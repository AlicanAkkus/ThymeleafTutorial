package com.wora.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import com.wora.facade.ServiceFacade;

public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}

	private void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServletContextTemplateResolver templateResolver = (ServletContextTemplateResolver) request.getServletContext().getAttribute("templateResolver");
		TemplateEngine engine = new TemplateEngine();
		engine.setTemplateResolver(templateResolver);

		WebContext ctx = new WebContext(request, response, getServletConfig().getServletContext(), request.getLocale());
		try {
			ctx.setVariable("orders", ServiceFacade.getInstance().getOrders());
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		String result = engine.process("thy", ctx);

		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.println(result);
		} finally {
			out.close();
		}
	}

}
