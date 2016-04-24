package com.vietsci.cms.frontend.etopup.converter;

import org.primefaces.convert.DateTimeConverter;

import javax.faces.convert.FacesConverter;
import java.util.TimeZone;

@FacesConverter("customDateTimeConverter")
public class CustomDateTimeConverter extends DateTimeConverter {

  /**
   * Constructor.
   */
  public CustomDateTimeConverter() {
    super();

    // set the default timezone to the system time zone instead of GMT
    super.setTimeZone(TimeZone.getTimeZone("Asia/Bangkok"));
    setPattern("dd/MM/yyyy");

  }
}
