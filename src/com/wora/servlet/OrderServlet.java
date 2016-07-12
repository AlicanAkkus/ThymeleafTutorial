package com.wora.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import com.wora.facade.ServiceFacade;
import com.wora.util.DateUtils;

public class OrderServlet extends HttpServlet {
	private static Logger logger = Logger.getLogger(LiteralsServlet.class);
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}

	private void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			ServletContextTemplateResolver templateResolver = (ServletContextTemplateResolver) request.getServletContext().getAttribute("templateResolver");
			TemplateEngine engine = new TemplateEngine();
			engine.setTemplateResolver(templateResolver);

			WebContext ctx = new WebContext(request, response, getServletConfig().getServletContext(), request.getLocale());
			ctx.setVariable("orders", ServiceFacade.getInstance().getOrders());
			ctx.setVariable("today", DateUtils.getLocalDate());
			String result = engine.process("thy", ctx);

			PrintWriter out = response.getWriter();
			out.println(result);
		} catch (Exception e) {
			logger.error(e, e);
		}

	}

}
