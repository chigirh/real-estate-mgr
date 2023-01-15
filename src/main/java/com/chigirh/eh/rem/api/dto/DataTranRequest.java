package com.chigirh.eh.rem.api.dto;

import lombok.Data;

@Data
public class DataTranRequest {
    private String reId;
    private String reName;
    private String reNameKana;
    private String area1;
    private String area2;
    private String area3;
    private String address;
    private Integer rentPrice;
    private Integer condoFee;
    private Integer waterFee;
    private String otherFee;
    private String mgrCompanyName;
    private String mgrCompanyTel;
    private String foreignerLiveSts;
    private String pdf;
    private String note;
    private String updatedAt;
}
