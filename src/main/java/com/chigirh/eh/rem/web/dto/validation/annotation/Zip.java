package com.chigirh.eh.rem.web.dto.validation.annotation;

import com.chigirh.eh.rem.web.dto.validation.validator.CsvValidator;
import com.chigirh.eh.rem.web.dto.validation.validator.ZipValidator;
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
@Constraint(validatedBy = ZipValidator.class)
public @interface Zip {
    String message() default "{re-mgr.common.validation.zip.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
