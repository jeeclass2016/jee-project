package com.vietsci.cms.api.webservice;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vietsci.cms.api.exception.BusinessException;
import com.vietsci.cms.api.http.EposResponse;
import com.vietsci.cms.api.util.Constants;

/**
 * BaseController, this is handling exception of controller classes
 * 
 */
@ControllerAdvice
public class BaseController {
  
  /**
   * AgentController is used to log.
   */
   private static final Log logger = LogFactory.getLog(BaseController.class);
  
  @ExceptionHandler(Exception.class)
  public @ResponseBody String handleException(Exception ex, WebRequest webRequest, HttpServletResponse response) throws IOException {
    response.setHeader("Content-Type", "application/json");
    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    
    logger.error("Exception occurred:", ex);
    
    EposResponse eposResponse = new EposResponse();
    eposResponse.setStatusCode(response.getStatus());
    if (ex instanceof BusinessException) {
      BusinessException be = (BusinessException) ex;
      eposResponse.setMessage(be.getMessage());
      eposResponse.setMessageCode(be.getMsgKey());
    } else {
      eposResponse.setMessage("Internal Server Error");
      eposResponse.setMessageCode(Constants.SYSTEM_ERROR_CODE);
    }
    
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.writeValueAsString(eposResponse);
  }
}
