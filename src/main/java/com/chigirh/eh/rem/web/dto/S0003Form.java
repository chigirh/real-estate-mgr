package com.chigirh.eh.rem.web.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
public class S0003Form {
    @NotBlank
    private String reName;
    @NotBlank
    @Pattern(regexp = "^[\\u30A0-\\u30FF]+$")
    private String reNameKana;
    @NotBlank
    private String area1;
    private String area2;
    private String area3;
    @NotBlank
    private String address;
    @NotNull
    private Integer rentPrice;
    private String mgrCompanyName;
    @Size(max = 13)
    private String mgrCompanyTel;
    private String foreignerLiveSts;
    private MultipartFile uploadFile;
    private String note;

    @AssertTrue(message = "pdfをアップロードしてください")
    public boolean isPdfFile() {
        if (uploadFile == null) {
            return false;
        }


        var fileName = uploadFile.getOriginalFilename();

        int idx = fileName.lastIndexOf(".");
        String extName = (-1 != idx) ? fileName.substring(idx + 1) : "";

        return "pdf".equals(extName);
    }
}
