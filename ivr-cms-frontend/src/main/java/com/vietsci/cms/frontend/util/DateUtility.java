package com.vietsci.cms.frontend.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Utility date
 */
public class DateUtility {
  public static final String DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";

  /**
   * Get current date time by format
   * 
   * @return String
   * @param dFormat date format 
   */
  public static String getCurrentDateTime(String dFormat) {
    DateFormat dateFormat = simpleDateFormat(dFormat);

    Calendar date = Calendar.getInstance();

    return dateFormat.format(date);
  }

  /**
   * @return Date
   */
  public static Date getCurrentDate() {

    Calendar date = Calendar.getInstance();

    return date.getTime();
  }

  /**
   * Convert date to format
   * 
   * @param date 
   * @param dFormat 
   * @return String
   */
  public static String getConvertDateTime(Date date, String dFormat) {
    DateFormat dateFormat = simpleDateFormat(dFormat);

    return dateFormat.format(date);
  }

  /**
   * Perform convert string to date format
   * 
   * @param dateInString 
   * @param dFormat 
   * @return Date
   * @throws ParseException 
   */
  public static Date getConvertStringToDate(String dateInString, String dFormat) throws ParseException {
    DateFormat dateFormat = simpleDateFormat(dFormat);

    Date date = dateFormat.parse(dateInString);
    return date;
  }

  /**
   * @param dFormat
   * @return
   */
  private static DateFormat simpleDateFormat(String dFormat) {
    // default format by DATE_FORMAT
    DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

    // format date by parameter custom
    if (dFormat != null) {
      dateFormat = new SimpleDateFormat(dFormat);
    }

    return dateFormat;
  }
  
  /**
   * 
   * @param date1
   * @param date2
   * @param formatDate
   * @return
   */
  public static int compareDate(String date1, String date2, String formatDate) {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatDate);
    try {
      Date dateOne = simpleDateFormat.parse(date1);
      Date dateTwo = simpleDateFormat.parse(date2);
      return dateOne.compareTo(dateTwo);
    } catch (ParseException e) {
      e.printStackTrace();
      return -2;
    }
  }

  /**
   * Check time range, startDate has to be less than or equal to end Date
   * @param startDate
   * @param endDate
   * @return TRUE if startDate <= endDate, FALSE if startDate > endDate
   *
   */
  public static boolean validateTimeRange(Date startDate, Date endDate) {
    if (startDate != null && endDate != null && startDate.after(endDate)) {
      return false;
    }

    return true;
  }
  /**
   * Get time after someDay 
   * @param someDay
   * @return
   */
  public static Date getDateAfterSomeDay(Date date, int someDay){
	  Calendar cal = Calendar.getInstance();
	  cal.setTime(date);
	  int day = cal.get(Calendar.DAY_OF_MONTH);
	  cal.set(Calendar.DAY_OF_MONTH, day + someDay);
	  return cal.getTime();
  }

  /**
   * Get the first date of last month
   * @return the first date of last month
   *
   */
  public static Date getFirstDateOfLastMonth() {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.MONTH, -1);
    calendar.set(Calendar.DATE, 1);

    return calendar.getTime();
  }

  /**
   * Get the last date of last month
   * @return the last date of last month
   */
  public static Date getLastDateOfLastMonth() {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.MONTH, -1);
    calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));

    return calendar.getTime();
  }
}
