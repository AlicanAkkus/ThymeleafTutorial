package com.wora.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.wora.facade.ServiceFacade;

public class DeleteOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger = LogManager.getLogger(DeleteOrderServlet.class);

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
			
			String orderId = request.getParameter("orderId");
			ServiceFacade.getInstance().deleteOrder(Long.valueOf(orderId));
			request.getRequestDispatcher("thy.html").forward(request, response);
			
		} catch (Exception e) {
			logger.error(e, e);
		}

	}

}
