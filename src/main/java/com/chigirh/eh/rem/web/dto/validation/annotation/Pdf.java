package com.chigirh.eh.rem.web.dto.validation.annotation;

import com.chigirh.eh.rem.web.dto.validation.validator.PdfValidator;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
@Constraint(validatedBy = PdfValidator.class)
public @interface Pdf {
    String message() default "{re-mgr.common.validation.pdf.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
