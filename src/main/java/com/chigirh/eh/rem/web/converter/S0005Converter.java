package com.chigirh.eh.rem.web.converter;

import com.chigirh.eh.rem.domain.model.RealEstate;
import com.chigirh.eh.rem.domain.port.RealEstatcFetchPort;
import com.chigirh.eh.rem.domain.port.RealEstateUpdatePort;
import com.chigirh.eh.rem.web.dto.S0005Form;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;

@Component
public class S0005Converter {
    public RealEstateUpdatePort.Input convert(String reId, S0005Form s0005Form) {
        var model = new RealEstate();
        model.setReId(reId);
        model.setReName(s0005Form.getReName());
        model.setReNameKana(s0005Form.getReNameKana());

        model.setAreas(new ArrayList<>());
        if (!StringUtils.isEmpty(s0005Form.getArea1())) {
            model.getAreas().add(s0005Form.getArea1());
        }
        if (!StringUtils.isEmpty(s0005Form.getArea2())) {
            model.getAreas().add(s0005Form.getArea2());
        }
        if (!StringUtils.isEmpty(s0005Form.getArea3())) {
            model.getAreas().add(s0005Form.getArea3());
        }

        model.setAddress(s0005Form.getAddress());
        model.setRentPrice(s0005Form.getRentPrice());
        model.setMgrCompanyName(s0005Form.getMgrCompanyName());
        model.setMgrCompanyTel(s0005Form.getMgrCompanyTel());
        model.setForeignerLiveSts(s0005Form.getForeignerLiveSts());
        model.setNote(s0005Form.getNote());

        model.setPdf(s0005Form.getPdf());

        return new RealEstateUpdatePort.Input(model);
    }

    public void convert(S0005Form form, RealEstatcFetchPort.Output output) {

        var model = output.result();
        form.setReName(model.getReName());
        form.setReNameKana(model.getReNameKana());

        var areas = model.getAreas();
        form.setArea1(areas.get(0));
        form.setArea2(2 <= areas.size() ? areas.get(1) : "");
        form.setArea3(3 <= areas.size() ? areas.get(2) : "");

        form.setAddress(model.getAddress());
        form.setRentPrice(model.getRentPrice());
        form.setMgrCompanyName(model.getMgrCompanyName());
        form.setMgrCompanyTel(model.getMgrCompanyTel());
        form.setForeignerLiveSts(model.getForeignerLiveSts());
        form.setNote(model.getNote());

        form.setPdf(model.getPdf());
    }
}
