package com.chigirh.eh.rem.api.controller;

import com.chigirh.eh.rem.api.dto.DataTranRequest;
import com.chigirh.eh.rem.domain.model.realestate.RealEstate;
import com.chigirh.eh.rem.domain.repository.realestate.RealEstateRepository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DataTranController {
    private final RealEstateRepository realEstateRepository;

    @PostMapping("/v1/api/data_tran")
    public ResponseEntity<String> post(@RequestBody DataTranRequest req) {
        var dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

        var model = new RealEstate();
        model.setReId(req.getReId());
        model.setReName(req.getReName());
        model.setReNameKana(req.getReNameKana());
        model.setAreas(new ArrayList<>());
        if (req.getArea1() != null) {
            model.getAreas().add(req.getArea1());
        }
        if (req.getArea2() != null) {
            model.getAreas().add(req.getArea2());
        }
        if (req.getArea3() != null) {
            model.getAreas().add(req.getArea3());
        }
        model.setAddress(req.getAddress());
        model.setRentPrice(req.getRentPrice());
        model.setCondoFee(req.getCondoFee());
        model.setWaterFee(req.getWaterFee());
        model.setOtherFee(req.getOtherFee());
        model.setMgrCompanyName(req.getMgrCompanyName());
        model.setMgrCompanyTel(req.getMgrCompanyTel());
        model.setForeignerLiveSts(req.getForeignerLiveSts());
        model.setNote(req.getNote());
        model.setPdf(req.getPdf());
        model.setUpdatedAt(LocalDateTime.parse(req.getUpdatedAt(), dtf));

        realEstateRepository.create(model);

        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }

    @GetMapping("/v1/api/data_tran")
    public ResponseEntity<String> get() {
        var models = realEstateRepository.fetchAll(0);

        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }
}
