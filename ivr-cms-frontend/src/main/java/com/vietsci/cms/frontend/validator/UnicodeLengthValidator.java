package com.vietsci.cms.frontend.validator;

import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.LengthValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 */
@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class UnicodeLengthValidator implements Validator {
  private static final String MAXIMUM_BYTES = "maximumBytes";

  @Override
  public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    if ((context == null) || (component == null)) {
      throw new NullPointerException();
    }
    if (value != null) {
      String converted = stringValue(value);
      int maxLength = Integer.parseInt((String) component.getAttributes().get(MAXIMUM_BYTES));
      try {
        if (converted.getBytes("UTF8").length > maxLength) {
          /*
           * Get message pattern from the message bundle.
           */
          String bundleName = context.getApplication().getMessageBundle();
          Locale locale = context.getViewRoot().getLocale();
          if (locale == null) {
            locale = Locale.getDefault();
          }
          ResourceBundle bundle = ResourceBundle.getBundle(bundleName, locale);
          String msgPattern = bundle.getString(LengthValidator.MAXIMUM_MESSAGE_ID);
          String summary = MessageFormat.format(msgPattern, maxLength, this.getLabel(context, component));
          throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, summary));
        }
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * <p>Return the specified attribute value, converted to a
   * <code>String</code>.</p>
   *
   * @param attributeValue The attribute value to be converted
   */
  private static String stringValue(Object attributeValue) {

    if (attributeValue == null) {
      return null;
    } else if (attributeValue instanceof String) {
      return (String) attributeValue;
    } else {
      return attributeValue.toString();
    }
  }

  /**
   * <p>Returns the <code>label</code> property from the specified
   * component.</p>
   *
   * @param context   - the <code>FacesContext</code> for the current request
   * @param component - the component of interest
   *
   * @return the label, if any, of the component
   */
  private Object getLabel(FacesContext context, UIComponent component) {

    Object o = component.getAttributes().get("label");
    if (o == null || (o instanceof String && ((String) o).length() == 0)) {
      o = component.getValueExpression("label");
    }
    // Use the "clientId" if there was no label specified.
    if (o == null) {
      o = component.getClientId(context);
    }
    return o;
  }
}
