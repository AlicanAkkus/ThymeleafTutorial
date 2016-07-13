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

import com.wora.bean.Order;
import com.wora.util.DateUtils;

public class LiteralsServlet extends HttpServlet {
	private static Logger logger = Logger.getLogger(LiteralsServlet.class);
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}

	private void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Literals page requested..");
		try {
			ServletContextTemplateResolver templateResolver = (ServletContextTemplateResolver) request.getServletContext().getAttribute("templateResolver");
			if(templateResolver != null){
				TemplateEngine engine = new TemplateEngine();
				engine.setTemplateResolver(templateResolver);
				
				WebContext ctx = new WebContext(request, response, getServletConfig().getServletContext(), request.getLocale());
				
				Order myOrder = new Order();
				myOrder.setId(112233);
				myOrder.setPrice(105.4);
				myOrder.setStatus(true);
				myOrder.setSummary("Phone");
				myOrder.setDetails("I bought a new phone for my sister!");
				
				ctx.setVariable("today", DateUtils.getLocalDate());
				ctx.setVariable("myOrder", myOrder);
				String result = engine.process("literals", ctx);
				
				PrintWriter out = response.getWriter();
				out.println(result);
			}
		} catch (Exception e) {
			logger.error(e, e);
		}

	}

}
