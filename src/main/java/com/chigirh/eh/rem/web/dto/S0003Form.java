package com.chigirh.eh.rem.web.dto;

import com.chigirh.eh.rem.web.dto.validation.annotation.Address;
import com.chigirh.eh.rem.web.dto.validation.annotation.Area;
import com.chigirh.eh.rem.web.dto.validation.annotation.RealEstateName;
import com.chigirh.eh.rem.web.dto.validation.annotation.RealEstateNameKana;
import com.chigirh.eh.rem.web.dto.validation.annotation.RentPrice;
import com.chigirh.eh.rem.web.dto.validation.annotation.Tel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.AssertTrue;

@Data
@NoArgsConstructor
public class S0003Form {
    @RealEstateName
    private String reName;
    @RealEstateNameKana
    private String reNameKana;
    @Area
    private String area1;
    private String area2;
    private String area3;
    @Address
    private String address;
    @RentPrice
    private Integer rentPrice;
    private String mgrCompanyName;
    @Tel
    private String mgrCompanyTel;
    private String foreignerLiveSts;
    private MultipartFile uploadFile;
    private String note;

    @AssertTrue(message = "{re-mgr.real-estate.validation.upload-file.message}")
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
