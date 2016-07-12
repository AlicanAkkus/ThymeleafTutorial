package com.wora.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;

public class DateUtils {
	
	private static Logger logger = Logger.getLogger(DateUtils.class);
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy");
	
	public static String getLocalDate() {
		logger.info("GetLocalDate method is started..");
		
		try{
			Calendar calendar = Calendar.getInstance();
			
			String localDate = dateFormat.format(calendar.getTime());
			return localDate;
		}catch(Exception e){
			logger.error(e, e);
		}
		
		return "01/01/1970";
	}
	
}
