package com.wora.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

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

public class AddOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger = LogManager.getLogger(AddOrderServlet.class);

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
			
			String orderDetails = request.getParameter("orderDescription");
			String orderId = request.getParameter("orderId");
			String orderPrice = request.getParameter("orderPrice");
			String orderSummary = request.getParameter("orderSummary");
			String orderStatus = request.getParameter("orderStatus");
			
			Order order = new Order();
			order.setDetails(orderDetails);
			order.setId(Long.valueOf(orderId));
			order.setPrice(Double.valueOf(orderPrice));
			order.setSummary(orderSummary);
			order.setStatus(Boolean.valueOf(orderStatus));

			logger.info(order.toString());
			ServiceFacade.getInstance().addOrder(order);
			ArrayList<Order> orders = ServiceFacade.getInstance().getOrders();

			ServletContextTemplateResolver templateResolver = (ServletContextTemplateResolver) request.getServletContext().getAttribute("templateResolver");
			TemplateEngine engine = new TemplateEngine();
			engine.setTemplateResolver(templateResolver);

			WebContext ctx = new WebContext(request, response, getServletConfig().getServletContext(), request.getLocale());
			ctx.setVariable("orders", orders);
			
			String result = engine.process("thyhello", ctx);

			PrintWriter out = null;
			try {
				out = response.getWriter();
				out.println(result);
			} finally {
				out.close();
			}

		} catch (Exception e) {
			logger.error(e, e);
		}

	}

}
