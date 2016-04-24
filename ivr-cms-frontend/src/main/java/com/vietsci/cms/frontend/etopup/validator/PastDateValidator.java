package com.vietsci.cms.frontend.etopup.validator;

import org.omnifaces.util.Messages;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.Date;

@FacesValidator("pastDateValidator")
public class PastDateValidator implements Validator {

  @Override
  public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    if(value == null) {
      return;
    }

    Date date = (Date) value;

    if(date.after(new Date())) {
      throw new ValidatorException(Messages.createError("Ngày hợp lệ là ngày trước hoặc bằng ngày hiện tại"));
    }

  }
}
