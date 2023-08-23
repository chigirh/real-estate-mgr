package com.chigirh.eh.rem.web.dto.validation.validator;

import com.chigirh.eh.rem.web.dto.validation.annotation.Csv;
import com.chigirh.eh.rem.web.dto.validation.annotation.Zip;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ZipValidator implements ConstraintValidator<Zip, MultipartFile> {
    @Override
    public void initialize(Zip constraint) {
    }

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext context) {
        if (multipartFile == null || multipartFile.isEmpty()) {
            return false;
        }

        var fileName = multipartFile.getOriginalFilename();

        int idx = fileName.lastIndexOf(".");
        String extName = (-1 != idx) ? fileName.substring(idx + 1) : "";

        return "zip".equals(extName);
    }
}
