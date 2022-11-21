package com.chigirh.eh.rem.web.dto.validation.validator;

import com.chigirh.eh.rem.web.dto.validation.annotation.Pdf;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PdfValidator implements ConstraintValidator<Pdf, MultipartFile> {
    @Override
    public void initialize(Pdf constraint) {
    }

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext context) {
        if (multipartFile == null || multipartFile.isEmpty()) {
            return false;
        }

        var fileName = multipartFile.getOriginalFilename();

        int idx = fileName.lastIndexOf(".");
        String extName = (-1 != idx) ? fileName.substring(idx + 1) : "";

        return "pdf".equals(extName);
    }
}
