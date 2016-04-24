package com.vietsci.cms.frontend.etopup.validator;

import org.omnifaces.util.Messages;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.regex.Pattern;

@FacesValidator("nameValidator")
public class NameValidator implements Validator {

  private static final Pattern PATTERN = Pattern.compile("[\\ \\t]*[a-zA-Z0-9]+[\\ \\t]*");

  @Override
  public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    if (value == null || value.toString().trim().equals("")) {
      return; // Let required="true" handle.
    }

    if (!PATTERN.matcher(value.toString()).matches()) {
      throw new ValidatorException(Messages.createError("Chỉ được nhập các chữ từ a -> z và các chữ số từ 0 -> 9"));
    }
  }

}