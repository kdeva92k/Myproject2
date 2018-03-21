package com.glenwood.glaceemr.server.application.controllers;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
@RequestMapping(value = "/user/log4j")
public class Log4jLevelChanger {

	protected final Logger logger = Logger.getLogger(Log4jLevelChanger.class);
	
	
	@RequestMapping(value = "/changeLevel", method = RequestMethod.GET)
	@ResponseBody
	public void Logleveltest(@RequestParam(value="level", required=false, defaultValue="DEBUG") String logLevel) throws Exception{

		Logger root = Logger.getRootLogger();
		boolean logLevelRecognized = true;
		if ("DEBUG".equalsIgnoreCase(logLevel)) {
			root.setLevel(Level.DEBUG);
		} else if ("INFO".equalsIgnoreCase(logLevel)) {
			root.setLevel(Level.INFO);
		} else if ("WARN".equalsIgnoreCase(logLevel)) {
			root.setLevel(Level.WARN);
		} else if ("ERROR".equalsIgnoreCase(logLevel)) {
			root.setLevel(Level.ERROR);
		} else if ("FATAL".equalsIgnoreCase(logLevel)) {
			root.setLevel(Level.FATAL);
		} else {
			logLevelRecognized = false;
		}
		logger.debug("debug message");
		logger.info("info message");
		logger.warn("warn message");
		logger.error("error message");
		logger.fatal("fatal message");

	}


}
