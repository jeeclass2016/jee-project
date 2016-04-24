package com.vietsci.cms.frontend.jsf.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vietsci.cms.frontend.main.controller.AuthorizationController;
/**
 * Authorization filter 
 * 
 */
public class AuthorizationFilter implements Filter {

  private FilterConfig config = null;
  
  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    this.config = filterConfig;
    config.getServletContext().log("Initializing Authorization Filter");
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
      ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpSession session = ((HttpServletRequest) request).getSession();
    AuthorizationController auth =  (session != null) ? (AuthorizationController) session.getAttribute("auth") : null;
    
    if (auth != null && auth.isLoggedIn()) {
      chain.doFilter(request, response);
    } else {
      HttpServletResponse res = (HttpServletResponse) response;
      res.sendRedirect(req.getContextPath() + "/login.xhtml");
    }
  }

  @Override
  public void destroy() {
    config.getServletContext().log("Destroy Authorization filter");
  }

}
