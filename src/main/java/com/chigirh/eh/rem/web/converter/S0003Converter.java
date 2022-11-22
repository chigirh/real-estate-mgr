package com.chigirh.eh.rem.web.converter;

import com.chigirh.eh.rem.domain.model.realestate.RealEstate;
import com.chigirh.eh.rem.domain.port.RealEstateCreatePort;
import com.chigirh.eh.rem.web.dto.S0003Form;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

@Component
@RequiredArgsConstructor
public class S0003Converter {

    private final PdfConverter pdfConverter;

    public RealEstateCreatePort.Input convert(S0003Form s0003Form) {
        var model = new RealEstate();
        model.setReName(s0003Form.getReName());
        model.setReNameKana(s0003Form.getReNameKana());

        model.setAreas(new ArrayList<>());
        if (!StringUtils.isEmpty(s0003Form.getArea1())) {
            model.getAreas().add(s0003Form.getArea1());
        }
        if (!StringUtils.isEmpty(s0003Form.getArea2())) {
            model.getAreas().add(s0003Form.getArea2());
        }
        if (!StringUtils.isEmpty(s0003Form.getArea3())) {
            model.getAreas().add(s0003Form.getArea3());
        }

        model.setAddress(s0003Form.getAddress());
        model.setRentPrice(s0003Form.getRentPrice());
        model.setCondoFee(s0003Form.getCondoFee());
        model.setWaterFee(s0003Form.getWaterFee());
        model.setOtherFee(s0003Form.getOtherFee());
        model.setMgrCompanyName(s0003Form.getMgrCompanyName());
        model.setMgrCompanyTel(s0003Form.getMgrCompanyTel());
        model.setForeignerLiveSts(s0003Form.getForeignerLiveSts());
        model.setNote(s0003Form.getNote());
        model.setPdf(pdfConverter.convert(s0003Form.getUploadFile()));
        return new RealEstateCreatePort.Input(model);
    }
}
