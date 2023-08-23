package com.chigirh.eh.fx.web.model;

import com.chigirh.eh.rem.web.dto.validation.annotation.Zip;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class OandaChartPostForm {
    @Zip
    private MultipartFile uploadFile;
}
