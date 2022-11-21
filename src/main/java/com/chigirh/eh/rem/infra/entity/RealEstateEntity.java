package com.chigirh.eh.rem.infra.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RealEstateEntity {
    private String reId;
    private String reName;
    private String reNameKana;
    private String initial;
    private String address;
    private int rentPrice;
    private Integer condoFee;
    private Integer waterFee;
    private String otherFee;
    private String mgrCompanyName;
    private String mgrCompanyTel;
    private String foreignerLiveSts;
    private String note;
    private String pdf;
    private LocalDateTime updatedAt;
}
