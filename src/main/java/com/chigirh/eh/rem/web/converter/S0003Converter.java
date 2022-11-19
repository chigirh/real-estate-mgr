package com.chigirh.eh.rem.web.converter;

import com.chigirh.eh.rem.domain.error.SystemError;
import com.chigirh.eh.rem.domain.model.realestate.RealEstate;
import com.chigirh.eh.rem.domain.port.RealEstateCreatePort;
import com.chigirh.eh.rem.web.dto.S0003Form;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;

@Component
public class S0003Converter {
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
        model.setMgrCompanyName(s0003Form.getMgrCompanyName());
        model.setMgrCompanyTel(s0003Form.getMgrCompanyTel());
        model.setForeignerLiveSts(s0003Form.getForeignerLiveSts());
        model.setNote(s0003Form.getNote());

        try {
            var pdfBinary = s0003Form.getUploadFile().getBytes();
            var base64Pdf = Base64.getEncoder().encodeToString(pdfBinary);
            model.setPdf(base64Pdf);
        } catch (IOException e) {
            throw new SystemError("system error.", e);
        }

        return new RealEstateCreatePort.Input(model);
    }
}
