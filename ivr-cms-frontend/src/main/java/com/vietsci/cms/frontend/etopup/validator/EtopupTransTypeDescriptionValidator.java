package com.vietsci.cms.frontend.etopup.validator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.omnifaces.util.Messages;

import com.vietsci.cms.frontend.etopup.common.util.Constants;

@FacesValidator("etopupTransTypeDescriptionValidator")
public class EtopupTransTypeDescriptionValidator implements Validator {

  @Override
  public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    if (value == null || value.toString().trim().equals("")) {
      throw new ValidatorException(Messages.createError(Constants.TransactionTypeManagement.VALIDATOR_MESSAGE_TRANS_TYPE_FIELD_NULL));
    }
    String valueStr = value.toString();
    for (String chr : Constants.SPECIAL_CHARACTERS) {
      if (valueStr.contains(chr)) {
        throw new ValidatorException(Messages.createError(Constants.TransactionTypeManagement.VALIDATOR_MESSAGE_TRANS_TYPE_DESC_CHARS));
      }
    }  
  }

}