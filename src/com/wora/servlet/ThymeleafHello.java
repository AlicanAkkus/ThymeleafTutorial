package com.wora.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

public class ThymeleafHello extends HttpServlet {
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
		ArrayList<String> languages = new ArrayList<>();
		languages.add("tr");
		languages.add("en");
		languages.add("fr");
		ctx.setVariable("languages", languages);
		ctx.setVariable("nick", "CaySever");

		String templateName = getTemplateName(request);
		String result = engine.process(templateName, ctx);

		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.println(result);
		} finally {
			out.close();
		}
	}

	protected String getTemplateName(HttpServletRequest request) {
		String requestPath = request.getRequestURI();
		String contextPath = request.getContextPath();
		if (contextPath == null) {
			contextPath = "";
		}

		return requestPath.substring(contextPath.length() + 1, requestPath.indexOf(".html"));
	}

}
