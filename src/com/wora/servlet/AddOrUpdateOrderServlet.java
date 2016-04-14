package com.wora.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import com.wora.bean.Order;
import com.wora.facade.ServiceFacade;

public class AddOrUpdateOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger = LogManager.getLogger(AddOrUpdateOrderServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		execute(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		execute(req, resp);
	}

	protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			
			Order order = new Order();
			order.setDetails(request.getParameter("orderDescription"));
			order.setId(Long.valueOf(request.getParameter("orderId")));
			order.setPrice(Double.valueOf(request.getParameter("orderPrice")));
			order.setSummary(request.getParameter("orderSummary"));
			order.setStatus(Boolean.valueOf(request.getParameter("orderStatus")));

			logger.info(order.toString());
			if(request.getParameter("action").equalsIgnoreCase("save")){
				ServiceFacade.getInstance().addOrder(order);
			}else if(request.getParameter("action").equalsIgnoreCase("update")){
				ServiceFacade.getInstance().updateOrder(order);
			}
			request.getRequestDispatcher("thy.html").forward(request, response);

		} catch (Exception e) {
			logger.error(e, e);
			
			ServletContextTemplateResolver templateResolver = (ServletContextTemplateResolver) request.getServletContext().getAttribute("templateResolver");
			TemplateEngine engine = new TemplateEngine();
			engine.setTemplateResolver(templateResolver);

			WebContext ctx = new WebContext(request, response, getServletConfig().getServletContext(), request.getLocale());
			ctx.setVariable("error", e.getMessage());
			String result = engine.process("error", ctx);
			
			PrintWriter out = null;
			try {
				out = response.getWriter();
				out.println(result);
			} finally {
				out.close();
			}
		}

	}

}
