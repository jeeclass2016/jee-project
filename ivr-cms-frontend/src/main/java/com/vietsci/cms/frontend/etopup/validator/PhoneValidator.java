package com.vietsci.cms.frontend.etopup.validator;

import org.omnifaces.util.Messages;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.regex.Pattern;

@FacesValidator("phoneValidator")
public class PhoneValidator implements Validator {

  private static final Pattern LENGTH_PATTERN = Pattern.compile("[\\ \\t]*\\d{10,15}[\\ \\t]*");
  private static final Pattern LENGTH_PATTERN_2 = Pattern.compile("[\\ \\t]*\\d{10,15}[\\ \\t]*");
  private static final Pattern NUMBER_PATTERN = Pattern.compile("[\\ \\t]*\\d+[\\ \\t]*");
  private static final Pattern START_WITH_PATTERN = Pattern.compile("[\\ \\t]*0.*");

  @Override
  public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    if (value == null || value.toString().trim().equals("")) {
      return; // Let required="true" handle.
    }

    if (!START_WITH_PATTERN.matcher(value.toString()).matches()) {
      throw new ValidatorException(Messages.createError("Số thuê bao luôn bắt đầu bằng số 0"));
    }
    if (!NUMBER_PATTERN.matcher(value.toString()).matches()) {
      throw new ValidatorException(Messages.createError("Số thuê bao phải là một số"));
    }
    if (!LENGTH_PATTERN.matcher(value.toString()).matches()) {
      throw new ValidatorException(Messages.createError("Số thuê bao có độ dài ít nhất là 10 kí tự và nhiều" +
        " nhất là 15 kí tự kể cả số 0"));
    }
  }
  
  public static String checkValidPhoneNumber(String value) {
    if (value == null || value.trim().equals("")) {
      return "Số thuê bao là trường bắt buộc";
    }
    if (!NUMBER_PATTERN.matcher(value).matches()) {
      return "Số thuê bao phải là một số";
    }
    if (START_WITH_PATTERN.matcher(value).matches() && !LENGTH_PATTERN.matcher(value).matches()) {
      return "Số thuê bao có độ dài ít nhất là 10 kí tự và nhiều" +
          " nhất là 15 kí tự kể cả số 0";
    }
    if (!START_WITH_PATTERN.matcher(value).matches() && !LENGTH_PATTERN_2.matcher(value).matches()) {
      return "Số thuê bao có độ dài ít nhất là 9 kí tự và nhiều" +
          " nhất là 14 kí tự không kể số 0";
    }
    return null;// no errors
  }
}