package com.vietsci.cms.frontend.etopup.common.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

/** 
 * Common Utils for the ETOPUP module.
 * 
 * @author nguyen.phanvan
 * Copyright © 2014 HTC
 * $LastChangedRevision: $
 * $LastChangedDate: $  
 */
public class EtopupUtil {

  private static final Pattern PHONE_NUMBER_LENGTH_PATTERN = Pattern.compile("\\d{10,15}");
  private static final Pattern ICCID_NUMBER_LENGTH_PATTERN = Pattern.compile("\\d{20}");
  private static final Pattern START_WITH_ZERO_PATTERN = Pattern.compile("0.*");
  private static final Pattern ALPHABET_AND_NUMBER_PATTERN = Pattern.compile("[a-zA-Z0-9]+");
  private static final Pattern NUMBER_PATTERN = Pattern.compile("\\d+");
  private static final Pattern MONEY_PATTERN = Pattern.compile("\\d+(\\.\\d+)?");

  public static Map<String, String> read(String jsonString) {
    Map<String,String> map = new HashMap<String,String>();
    ObjectMapper mapper = new ObjectMapper();
   
    try { 
      //convert JSON string to Map
      map = mapper.readValue(jsonString, 
          new TypeReference<HashMap<String,String>>(){});
   
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return map;
  }
  
  /**
   * Check phone number length. It must be from 10 to 15 characters.
   * @param phoneNumberStr
   * @return
   */
  public static boolean validatePhoneNumberLength(String phoneNumberStr) {
    return PHONE_NUMBER_LENGTH_PATTERN.matcher(phoneNumberStr).matches();
  }
  
  /**
   * Check ICCID length. It must be 20 characters.
   * @param iccid
   * @return
   */
  public static boolean validateICCIDLength(String iccid) {
    return ICCID_NUMBER_LENGTH_PATTERN.matcher(iccid).matches();
  }
  
  /**
   * Check a string start with 0 character or not.
   * @param str
   * @return
   */
  public static boolean validateStartWithZero(String str) {
    return START_WITH_ZERO_PATTERN.matcher(str).matches();
  }
  
  /**
   * Validate string contain only 
   * @param str
   * @return
   */
  public static boolean validateAlphabetAndNumberCharacterOnly(String str) {
    return ALPHABET_AND_NUMBER_PATTERN.matcher(str).matches();
  }
  
  /**
   * @param str
   * @return
   */
  public static boolean validateNumberCharacterOnly(String str) {
    return NUMBER_PATTERN.matcher(str).matches();
  }
  
  
  public static void main(String[] args) {
    Map<String,String> map = read("{\"statusCode\":500,\"messageCode\":\"0102\","
        + "\"message\":\"Reason code already existed.\"}");
    System.out.println(map);
  }

  /**
   * Check time range, startDate has to be less than or equal to end Date
   * @param startDate
   * @param endDate
   * @return TRUE if startDate <= endDate, FALSE if startDate > endDate
   *
   * @author hong.nguyenmai
   */
  public static boolean validateTimeRange(Date startDate, Date endDate) {
    if (startDate != null && endDate != null && startDate.after(endDate)) {
      return false;
    }

    return true;
  }

  public static boolean validateMoneyPattern(String str) {
    return MONEY_PATTERN.matcher(str).matches();
  }

}
