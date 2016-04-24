package com.vietsci.cms.frontend.etopup.validator;

import org.omnifaces.util.Messages;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.regex.Pattern;

@FacesValidator("idNoValidator")
public class IdNoValidator implements Validator {

  private static final Pattern LENGTH_PATTERN = Pattern.compile("[\\ \\t]*\\d{9,12}[\\ \\t]*");

  @Override
  public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    if (value == null || value.toString().trim().equals("")) {
      return;
    }

    if (!LENGTH_PATTERN.matcher(value.toString()).matches()) {
      throw new ValidatorException(Messages.createError("Số CMT chứa 9 đến 12 kí tự số"));
    }
  }
}
