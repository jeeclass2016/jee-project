package com.vietsci.cms.frontend.etopup.validator;

import org.omnifaces.util.Messages;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("positiveNumberValidator")
public class PositiveNumberValidator implements Validator {

  @Override
  public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    if (value == null || value.toString().trim().equals("")) {
      return; // Let required="true" handle.
    }

    double number = Double.parseDouble(value.toString());
    if (number < 0) {
      throw new ValidatorException(Messages.createError("Số nhập vào phải lớn hơn hoặc bằng 0"));
    }
  }
}
