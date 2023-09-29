package com.mayuratech.api.logging;
import org.apache.logging.log4j.*;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Log4JDemo {
	private static Logger demologger = LogManager.getLogger(Log4JDemo.class.getName());
	public void logs() {
		demologger.info("Application started Successfull");
	}
		
}
