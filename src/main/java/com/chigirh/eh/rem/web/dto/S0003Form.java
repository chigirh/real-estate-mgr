package com.chigirh.eh.rem.web.dto;

import com.chigirh.eh.rem.web.dto.validation.annotation.Address;
import com.chigirh.eh.rem.web.dto.validation.annotation.Area;
import com.chigirh.eh.rem.web.dto.validation.annotation.Pdf;
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
    private Integer condoFee;
    private Integer waterFee;
    private String otherFee;
    private String mgrCompanyName;
    @Tel
    private String mgrCompanyTel;
    private String foreignerLiveSts;
    @Pdf
    private MultipartFile uploadFile;
    private String note;
}
