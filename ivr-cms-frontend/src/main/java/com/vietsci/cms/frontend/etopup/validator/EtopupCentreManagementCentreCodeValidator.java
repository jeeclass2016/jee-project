package com.vietsci.cms.frontend.etopup.validator;

import org.omnifaces.util.Messages;

import com.vietsci.cms.frontend.etopup.common.util.Constants;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import java.util.regex.Pattern;

@FacesValidator("etopupCentreManagementCentreCodeValidator")
public class EtopupCentreManagementCentreCodeValidator implements Validator {

	private static final Pattern PATTERN_CHARS = Pattern.compile("^\\w*$");
	private static final Pattern PATTERN_WORD_LENGTH = Pattern.compile("^\\w{1,10}$");

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if (value == null || value.toString().trim().equals("")) {
		  throw new ValidatorException(Messages.createError(Constants.CentreManagement.VALIDATOR_MESSAGE_CENTRE_FIELD_NULL));
		}
		
    if(!PATTERN_CHARS.matcher(value.toString().replaceAll(" ", "")).matches()){
      throw new ValidatorException(Messages.createError(Constants.CentreManagement.VALIDATOR_MESSAGE_CENTRE_CODE_CHARS));
    }
		
    if(!PATTERN_WORD_LENGTH.matcher(value.toString().replaceAll(" ", "")).matches()){
      throw new ValidatorException(Messages.createError(Constants.CentreManagement.VALIDATOR_MESSAGE_CENTRE_CODE_LENGTH));
    }
	}

}