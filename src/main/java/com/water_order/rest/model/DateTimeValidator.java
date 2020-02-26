package com.water_order.rest.model;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeValidator implements ConstraintValidator<ValidDateTime, String> {
  private static final String DATE_PATTERN = "yyyy/MM/dd";
  private SimpleDateFormat sdf;

  @Override
  public void initialize(ValidDateTime constraintAnnotation) {
    sdf = new SimpleDateFormat();
    sdf.applyPattern(DATE_PATTERN);
    sdf.setLenient(false);
  }

  @Override
  public boolean isValid(String object, ConstraintValidatorContext constraintContext) {
    if (object == null) {
      return false;
    }

    try {
      Date d = sdf.parse(object);
      return true;
    } catch (Exception ex) {
      return false;
    }
  }
}
