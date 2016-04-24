/**
 * 
 */
package com.vietsci.cms.api.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * CommonUtil
 * 
 */
public class CommonUtil {
  
  
  public static Date formatStringToDate(String format ,String date){
    SimpleDateFormat mySimpleDateFormat = new SimpleDateFormat(format);
    try {
      return mySimpleDateFormat.parse(date);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return null;
  } 
  
  public static String formatDateToString(String format ,Date date){
    SimpleDateFormat mySimpleDateFormat = new SimpleDateFormat(format);
    try {
      return mySimpleDateFormat.format(date);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  } 
  
  public static Date getCurrentTime(){
    return Calendar.getInstance().getTime();
  }
  
  public static boolean isDeletedRecord(Boolean isDeleted) {
    return isDeleted==null?false:isDeleted;
  }
  
  public static String formatMoneyToString(Object amount) {
    DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols();
    formatSymbols.setGroupingSeparator(',');
    formatSymbols.setDecimalSeparator('.');
    DecimalFormat df = new DecimalFormat("###,###.####", formatSymbols);
    return df.format(amount);
  }
  
  public static void main(String [] args){
    String startDateString = "06-27-2007";
    String datetime = "13-FEB-16";
    Date d = formatStringToDate("MM-dd-yyyy", startDateString);
    Date d1 = formatStringToDate("yyyy-dd-mm", startDateString);
    Date d2 = formatStringToDate("MM-dd-yyyy", datetime);
    Date d3 = formatStringToDate("yyyy-dd-mm", datetime);
    System.out.println(d);
    System.out.println(d1);
    System.out.println(d2);
    System.out.println(d3);
    System.out.println("xx:" + formatDateToString("yyyyMMddHHmmss", new Date()));
  }
}
