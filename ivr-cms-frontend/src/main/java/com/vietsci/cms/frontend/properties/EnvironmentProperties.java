package com.vietsci.cms.frontend.properties;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Environment class loader
 * 
 */
@SuppressWarnings("unchecked")
public class EnvironmentProperties {
  /**
   * Environment data as map
   */
  private static Map<String, String> data = new HashMap<String, String>();
  
  static {
    Properties properties = new Properties();
    try {
      // load properties file
      properties.load(MasterDataProperties.class.getResourceAsStream("/environment.properties"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    Enumeration<String> enumeration = (Enumeration<String>) properties.propertyNames();
    while (enumeration.hasMoreElements()) {
      String key = (String) enumeration.nextElement();
      String[] splitKey = key.split("\\.");
      data.put(splitKey[1], properties.getProperty(key));
    }
  }
  
  /**
   * Get data from properties map based on key
   * 
   * @param key the property key
   * @return data would be returned
   */
  public static String getData(String key) {
    if (data.containsKey(key)) {
      return data.get(key);
    }
    return "";
  }
}
