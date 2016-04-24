package com.vietsci.cms.frontend.util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.component.visit.VisitCallback;
import javax.faces.component.visit.VisitContext;
import javax.faces.component.visit.VisitResult;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.sun.faces.component.visit.FullVisitContext;

/**
 * Class chứa các method thực hiện các tiện ích của JSF
 */
public class FacesUtil {

  /**
   * Add add message
   *  
   * @param message the message would be displayed
   * @param severity severity
   */
  private static void addMessage(String message, Severity severity) {
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, message, message));
  }
  
  /**
   * Add add message had title
   *  
   * @param message the message would be displayed
   * @param severity severity
   */
  private static void addMessage(String title, String message, Severity severity) {
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, title, message));
  }

  /**
   * Add error message
   * 
   * @param msg the error message
   */
  public static void addErrorMessage(String msg) {
    addMessage(msg, FacesMessage.SEVERITY_ERROR);
  }
  
  /**
   * Add error message
   * 
   * @param msg the error message
   */
  public static void addErrorTitleMessage(String title, String msg) {
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, title, msg ));
  }
  
  /**
   * Add error message, has title of message
   * 
   * @param msg the error message
   * @param title title of message
   */
  public static void addErrorMsg(String controlId, String title, String msg) {
    FacesContext.getCurrentInstance().addMessage(controlId, new FacesMessage(FacesMessage.SEVERITY_ERROR, title, msg ));
  }

  /**
   * Add error message theo control ui
   * @param controlId id của ui component
   * @param msg  
   */
  public static void addErrorMessage(String controlId, String msg) {
    FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
    FacesContext.getCurrentInstance().addMessage(controlId, facesMsg);
  }

  /**
   * Add success message
   * 
   * @param msg the success message
   */
  public static void addSuccessMessage(String msg) {
    addMessage(msg, FacesMessage.SEVERITY_INFO);
  }
  
  public static void addSuccessMessage(String title, String msg) {
    addMessage(title, msg, FacesMessage.SEVERITY_INFO);
  }

  /**
   * Add warning message
   * @param msg the warning message
   */
  public static void addWarningMessage(String msg) {
    addMessage(msg, FacesMessage.SEVERITY_WARN);
  }

  /**
   * Add warning message has title
   * @param msg the warning message
   */
  public static void addWarningMessage(String title, String msg) {
    addMessage(title, msg, FacesMessage.SEVERITY_WARN);
  }
  
  /**
   * Get servlet request based on facesContext
   * 
   * @return instance of servlet request
   */
  public static HttpServletRequest getServletRequest() {
    return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
  }

  /**
   * Get request parameter
   * 
   * @param name the parameter name
   * @return value of request parameter
   */
  public static String getRequestParameter(String name) {
    return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(name);
  }

  /**
   * Get servlet context based on current instance of faces context
   * 
   * @return the servlet context
   */
  public static ServletContext getServletContext() {
    return (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
  }
  
  /**
   * Get context path based on current instance of faces context
   * 
   * @return context path
   */
  public static String getContextPath() {
    return FacesUtil.getServletContext().getContextPath();
  }
  
  /**
   * Find component by id
   * 
   * @author http://stackoverflow.com/questions/14378437/find-component-by-id-in-jsf
   * @param id component id
   * @return component 
   */
  public static UIComponent findComponent(final String id) {
    FacesContext context = FacesContext.getCurrentInstance(); 
    UIViewRoot root = context.getViewRoot();
    final UIComponent[] found = new UIComponent[1];
    root.visitTree(new FullVisitContext(context), new VisitCallback() {     
        public VisitResult visit(VisitContext context, UIComponent component) {
            if(component.getId().equals(id)){
                found[0] = component;
                return VisitResult.COMPLETE;
            }
            return VisitResult.ACCEPT;              
        }
    });
    return found[0];
  }
  
}
