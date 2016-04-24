package com.vietsci.cms.frontend.main.controller;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.vietsci.cms.frontend.util.Constants;

/**
 * Language controller
 * Change current language purpose  
 * 
 */
@Named
public class LanguageController implements Serializable {

  private static final long serialVersionUID = -6067201966358718626L;
  
  private Locale currentLocale;
  private Map<String, String> errorMap;
  private Map<String, String> msgMap;
  
  @PostConstruct
  public void init() {
    currentLocale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
    if (currentLocale != null && !Constants.LANGUAGE_VI.equals(currentLocale.getLanguage())) {
      currentLocale = new Locale(Constants.LANGUAGE_VI);
      FacesContext.getCurrentInstance().getViewRoot().setLocale(currentLocale); 
    }
    
    // get message bundle
    errorMap = loadData(Constants.ERROR_MESSAGE, currentLocale);
    msgMap = loadData(Constants.MSG_MESSAGE, currentLocale);
  }
  
  /**
   * Change current language
   * 
   * @param language woulbe changed
   */
  public void changeLanguage(String language) {
    
    String lg = language;
    if (!Constants.LANGUAGE_VI.equals(lg) && !Constants.LANGUAGE_EN.equals(lg)) {
      lg = Constants.LANGUAGE_VI;
    }
    
    currentLocale = new Locale(language);
    FacesContext.getCurrentInstance().getViewRoot().setLocale(currentLocale);
    
    // re-load resource bundle when changed language
    errorMap.clear();
    msgMap.clear();
    errorMap = loadData(Constants.ERROR_MESSAGE, currentLocale);
    msgMap = loadData(Constants.MSG_MESSAGE, currentLocale);
  }
  
  /**
   * Load all message in resource bundle
   * 
   * @param baseName bundle name
   * @param locale locale
   * @return a map of messages
   */
  public Map<String, String> loadData(String baseName, Locale locale) {
    Map<String, String> messageMap = new HashMap<String, String>();
    // get resource bundle
    ResourceBundle resourceBundle = ResourceBundle.getBundle(baseName, locale);
    Enumeration<String> enumeration  = (Enumeration<String>) resourceBundle.getKeys();
    while (enumeration.hasMoreElements()) {
        String key = (String) enumeration.nextElement();
        String value = resourceBundle.getString(key);
        messageMap.put(key, value);
    }
    return messageMap;
  }
  
  // getter-setter
  public Locale getCurrentLocale() {
    return currentLocale;
  }

  public Map<String, String> getErrorMap() {
    return errorMap;
  }

  public void setErrorMap(Map<String, String> errorMap) {
    this.errorMap = errorMap;
  }

  public Map<String, String> getMsgMap() {
    return msgMap;
  }

  public void setMsgMap(Map<String, String> msgMap) {
    this.msgMap = msgMap;
  }
}
