package com.wora.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import com.wora.facade.ServiceFacade;

/**
 * @author wora you can find more info at @see http://alicanakkus.com
 * */

public class TemplateInitializer implements ServletContextListener {

	private Logger logger = LogManager.getLogger(TemplateInitializer.class);

	public void contextInitialized(ServletContextEvent sce) {
		logger.info("Context initializing..");

		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
		// XHTML is the default mode, but we will set it anyway for better understanding of code
		templateResolver.setTemplateMode("XHTML");
		templateResolver.setPrefix("/WEB-INF/");
		templateResolver.setSuffix(".html");
		templateResolver.setCacheTTLMs(3600000L);
		
		sce.getServletContext().setAttribute("templateResolver", templateResolver);
		
		ServiceFacade.getInstance().startService();
		
		logger.info("Context initialized..");
	}

	public void contextDestroyed(ServletContextEvent sce) {
	}
}
