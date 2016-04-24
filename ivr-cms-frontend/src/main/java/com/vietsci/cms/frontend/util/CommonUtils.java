package com.vietsci.cms.frontend.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vietsci.cms.frontend.resttemplate.CmsRestTemplate;

/**
 * Store the methods common for project
 */
public class CommonUtils {

  /**
   * Gửi POST request đến một URL nào đó
   * 
   * @param url
   *          URL
   * @param object
   *          đối tượng cần gửi
   * @return Kết quả trả về từ server
   * @throws JsonProcessingException
   */
  public static String postRequest(String url, Object object) throws JsonProcessingException {

    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.setContentType(new MediaType("application", "x-www-form-urlencoded"));

    RestTemplate restTemplate = new CmsRestTemplate();
    restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
    restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

    ObjectMapper mapper = new ObjectMapper();

    MultiValueMap<String, Object> formData = new LinkedMultiValueMap<String, Object>();
    formData.add("data", mapper.writeValueAsString(object));

    return restTemplate.postForObject(url, formData, String.class);
  }

  /**
   * Kiểm tra input field có hợp lệ không 
   * 
   * @param input  
   * @return boolean 
   */
  public static boolean isValidate(String input, String sample) {
    if (StringUtils.isEmpty(input) || StringUtils.isEmpty(sample) || input.startsWith("-", 0) || input.startsWith("_", 0)) {
      return false;
    }
    Pattern p = Pattern.compile(sample);
    Matcher matcher = p.matcher(input);
    return matcher.matches();
  }
  /**
   * Kiểm tra input field có hợp lệ không 
   * 
   * @param input  
   * @return boolean 
   */
  public static boolean isValidateInputText(String input, String sample) {
    if (StringUtils.isBlank(input) || StringUtils.isBlank(sample) || input.startsWith("-",0) == true) {
      return false;
    }
    Pattern p = Pattern.compile(sample);
    Matcher matcher = p.matcher(input);
    return matcher.matches();
  }

  public static String formatMoneyToString(Object amount) {
    DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols();
    formatSymbols.setGroupingSeparator(',');
    formatSymbols.setDecimalSeparator('.');
    DecimalFormat df = new DecimalFormat("###,###.####", formatSymbols);
    return df.format(amount);
  }
}
