package com.chigirh.eh.rem.web.dto.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
@Constraint(validatedBy = {})
@NotBlank(message = "{re-mgr.real-estate.validation.re-name-kana.message}")
@Pattern(regexp = "^[\\u30A0-\\u30FF\\d]+$", message = "{re-mgr.real-estate.validation.re-name-kana.message}")
public @interface RealEstateNameKana {
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
