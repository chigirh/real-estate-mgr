package com.chigirh.eh.rem.web.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class S0005Form {
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
    private String pdf;
    private String note;
}
