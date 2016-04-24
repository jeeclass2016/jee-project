package com.vietsci.cms.frontend.etopup.validator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.omnifaces.util.Messages;

import com.vietsci.cms.frontend.etopup.common.util.Constants;

@FacesValidator("etopupReasonDescriptionValidator")
public class EtopupReasonDescriptionValidator implements Validator {
  
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if (value == null || value.toString().trim().equals("")) {
		  throw new ValidatorException(Messages.createError(Constants.ReasonManagement.VALIDATOR_MESSAGE_REASON_FIELD_NULL));
		}
		String valueStr = value.toString();
		for (String chr : Constants.SPECIAL_CHARACTERS) {
      if (valueStr.contains(chr)) {
        throw new ValidatorException(Messages.createError(Constants.ReasonManagement.VALIDATOR_MESSAGE_REASON_DESC_CHARS));
      }
		}  
	}

}