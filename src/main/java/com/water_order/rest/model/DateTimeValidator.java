package com.water_order.rest.model;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.format.DateTimeFormatter;

public class DateTimeValidator implements ConstraintValidator<ValidDateTime, String> {
  private final Logger log = LoggerFactory.getLogger(DateTimeValidator.class);

  private String pattern;

  @Override
  public void initialize(ValidDateTime constraintAnnotation) {
    this.pattern = constraintAnnotation.pattern();
  }

  @Override
  public boolean isValid(String object, ConstraintValidatorContext constraintContext) {
    if (object == null) {
      return true;
    }

    try {
      DateTimeFormatter.ofPattern(pattern).parse(object);
      return true;
    } catch (Exception ex) {
      log.error(object + " cannot be parsed by format " + pattern, ex);
      return false;
    }
  }
}
