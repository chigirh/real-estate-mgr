package com.chigirh.eh.rem.domain.model.realestate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RealEstate {
    // UUID
    private String reId;
    private String reName;
    private String reNameKana;
    private List<String> areas;
    private String address;
    private int rentPrice;
    private String mgrCompanyName;
    private String mgrCompanyTel;
    private String foreignerLiveSts;
    private String note;
    // Base64
    private String pdf;
    private LocalDateTime updatedAt;
}
