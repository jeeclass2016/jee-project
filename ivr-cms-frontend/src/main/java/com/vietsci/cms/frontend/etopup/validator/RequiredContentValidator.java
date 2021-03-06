package com.vietsci.cms.frontend.etopup.validator;


import org.omnifaces.util.Messages;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.regex.Pattern;

@FacesValidator("requiredContentValidator")
public class RequiredContentValidator implements Validator {

  private static final Pattern PATTERN = Pattern.compile("\\s+");

  @Override
  public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    if (value == null) {
      return; // Let required="true" handle.
    }

    if (PATTERN.matcher(value.toString()).matches()) {
      throw new ValidatorException(Messages.createError("Phải nhập ít nhất một ký tự khác khoảng trắng"));
    }
  }

}