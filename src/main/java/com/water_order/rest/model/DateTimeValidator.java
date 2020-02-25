package com.water_order.rest.model;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
//import java.time.format.DateTimeFormatter;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateTimeValidator implements ConstraintValidator<ValidDateTime, String> {
  private String pattern;

  @Override
  public void initialize(ValidDateTime constraintAnnotation) {
  }

  @Override
  public boolean isValid(String object, ConstraintValidatorContext constraintContext) {
    if (object == null) {
      return false;
    }

    try {
      DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss");
      DateTime dt = formatter.parseDateTime(object);
      return true;
    } catch (Exception ex) {
      return false;
    }
  }
}
