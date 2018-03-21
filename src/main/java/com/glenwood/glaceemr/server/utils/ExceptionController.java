package com.glenwood.glaceemr.server.utils;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
* @author Anupama
* class to handle all kinds of Exceptions
*
* throw the exception in controller class in a try/catch block or give default throws declaration
* Exception can be Default Exception or custom exception. eg.UserNotFoundException or GenericException
* Example handler is given for default Exceptions
* Commented code contains a similar Exception handler declaration to handle custom Exceptions
*
* Add handler for your Exceptions(Custom / Default) with a custom message
*/

@ControllerAdvice
public class ExceptionController {

     @Resource
     EMRResponseBean emrResponseBean;
     
     @Autowired
     SessionMap sessionMap;
     
     /**
      *
      * @return emrResponseBean object with custom values set
      * by default success value is expected to be false in case of exceptions
      * and data as null
      *
      * you can change data by adding any custom message in case of exceptions and
      * modify your client side code as required
      */
     protected final Logger logger = Logger.getLogger(ExceptionController.class);
  
     
     @ExceptionHandler(CustomException.class)
     public @ResponseBody EMRResponseBean handleCustom(CustomException ex) {
    	 logger.error("", ex);
    	 ex.printStackTrace();
         emrResponseBean.setSuccess(false);
         emrResponseBean.setData(null);
         return emrResponseBean;
     }
      @ExceptionHandler(Exception.class)
     public @ResponseBody EMRResponseBean handleCustomException(Exception ex) {
    	 logger.error("", ex);
    	 ex.printStackTrace();
         emrResponseBean.setSuccess(false);
         emrResponseBean.setData(null);
         return emrResponseBean;
     }
}
