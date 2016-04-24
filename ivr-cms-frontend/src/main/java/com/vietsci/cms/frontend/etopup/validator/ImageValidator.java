package com.vietsci.cms.frontend.etopup.validator;

import com.vietsci.cms.frontend.etopup.common.util.EtopupStringUtil;
import com.vietsci.cms.frontend.util.FacesUtil;

import org.apache.commons.io.FilenameUtils;
import org.omnifaces.util.Messages;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import java.util.Arrays;

@FacesValidator("imageValidator")
public class ImageValidator implements Validator {

  private final static String[] allowTypes = new String[] {"pdf", "jpg", "jpeg", "doc", "img", "docx"};

  @Override
  public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

    String uploadedFileName = (String) value;

    if(!EtopupStringUtil.isNullOrBlank(uploadedFileName)) {
      String extension = FilenameUtils.getExtension(uploadedFileName);
      if(!Arrays.asList(allowTypes).contains(extension)) {
        throw new ValidatorException(Messages.createError("File không đúng định dạng (.pdf, .jpg, .jpeg, .doc, .img, .docx)"));
      }
    }

  }
}
