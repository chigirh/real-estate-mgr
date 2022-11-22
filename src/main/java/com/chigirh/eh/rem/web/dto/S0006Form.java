package com.chigirh.eh.rem.web.dto;

import com.chigirh.eh.rem.web.dto.validation.annotation.Csv;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
public class S0006Form {
    @Csv
    private MultipartFile uploadFile;
}
