package com.vietsci.cms.frontend.exception;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;

public class CmsRestExceptionHandler implements ResponseErrorHandler {
  
  private ResponseErrorHandler errorHandler = new DefaultResponseErrorHandler();

  
  @Override
  public boolean hasError(ClientHttpResponse response) throws IOException {
    return errorHandler.hasError(response);
  }

  @Override
  public void handleError(ClientHttpResponse response) throws IOException {
    throw new CmsRestException(IOUtils.toString(response.getBody()));
  }
}
