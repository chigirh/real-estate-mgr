package com.chigirh.eh.rem.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({
    "物件名",
    "物件名(カナ)",
    "エリア1",
    "エリア2",
    "エリア3",
    "住所",
    "家賃",
    "共益費",
    "水町費",
    "その他費用",
    "管理会社",
    "管理会社TEL",
    "外国人入居可否",
    "備考:案内方法",
    "備考:保証会社",
    "備考:初期費用",
    "備考:その他",
    "URL"
})
public class S0006CsvData {
    @JsonProperty("物件名")
    private String reName;
    @JsonProperty("物件名(カナ)")
    private String reNameKana;
    @JsonProperty("エリア1")
    private String area1;
    @JsonProperty("エリア2")
    private String area2;
    @JsonProperty("エリア3")
    private String area3;
    @JsonProperty("住所")
    private String address;
    @JsonProperty("家賃")
    private Integer rentPrice;
    @JsonProperty("共益費")
    private Integer condoFee;
    @JsonProperty("水町費")
    private Integer waterFee;
    @JsonProperty("その他費用")
    private String otherFee;
    @JsonProperty("管理会社")
    private String mgrCompanyName;
    @JsonProperty("管理会社TEL")
    private String mgrCompanyTel;
    @JsonProperty("外国人入居可否")
    private String foreignerLiveSts;
    @JsonProperty("備考:案内方法")
    private String noteGuide;
    @JsonProperty("備考:保証会社")
    private String noteGuaranteeCompany;
    @JsonProperty("備考:初期費用")
    private String noteIniCost;
    @JsonProperty("備考:その他")
    private String noteOther;
    @JsonProperty("URL")
    private String url;


}
