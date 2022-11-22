package com.chigirh.eh.rem.web.converter;

import com.chigirh.eh.rem.domain.error.SystemError;
import java.io.IOException;
import java.util.Base64;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class PdfConverter {
    public String convert(MultipartFile multipartFile) {
        try {
            if (multipartFile != null && !multipartFile.isEmpty()) {
                var pdfBinary = multipartFile.getBytes();
                var base64Pdf = Base64.getEncoder().encodeToString(pdfBinary);
                return base64Pdf;
            } else {
                return "";
            }
        } catch (IOException e) {
            throw new SystemError("system error.", e);
        }
    }
}
