package com.chigirh.eh.rem.web.dto.validation.annotation;

import com.chigirh.eh.rem.web.dto.validation.validator.CsvValidator;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
@Constraint(validatedBy = CsvValidator.class)
public @interface Csv {
    String message() default "{re-mgr.common.validation.csv.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
