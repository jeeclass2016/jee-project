package com.vietsci.cms.frontend.etopup.validator;

import org.omnifaces.util.Messages;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.Calendar;
import java.util.Date;

@FacesValidator("ageValidator")
public class AgeValidator implements Validator {

  @Override
  public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    if(value == null) {
      return;
    }

    Date dobDate = (Date) value;

    Calendar dob = Calendar.getInstance();
    dob.setTime(dobDate);
    Calendar today = Calendar.getInstance();

    int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
    if(today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
      age--;
    }

    if(age < 14 || age > 100) {
      throw new ValidatorException(Messages.createError("Tuổi hợp lệ là từ 14 đến 100 tuổi"));
    }

  }
}
