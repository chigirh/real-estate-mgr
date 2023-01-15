package com.chigirh.eh.rem.batches;

import com.chigirh.eh.rem.api.dto.DataTranRequest;
import com.chigirh.eh.rem.domain.repository.realestate.RealEstateRepository;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataTranProcess {

    private final RealEstateRepository realEstateRepository;
    public boolean was = false;

    @Scheduled(fixedDelay = 999999)
    public void process() {
        if (was) {
            return;
        }
        was = true;

        var restTemplate = new RestTemplate();

        var dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

        var offset = 0;
        while (true) {
            var models = realEstateRepository.fetchAll(offset);

            if (models.getRecord().isEmpty()) {
                break;
            }

            System.out.println("post" + offset);
            models.getRecord().stream().map(e -> {
                    var req = new DataTranRequest();
                    req.setReId(e.getReId());
                    req.setReName(e.getReName());
                    req.setReNameKana(e.getReNameKana());
                    if (e.getAreas().size() == 3) {
                        req.setArea1(e.getAreas().get(0));
                        req.setArea2(e.getAreas().get(1));
                        req.setArea3(e.getAreas().get(2));
                    }
                    if (e.getAreas().size() == 2) {
                        req.setArea1(e.getAreas().get(0));
                        req.setArea2(e.getAreas().get(1));
                    }
                    if (e.getAreas().size() == 1) {
                        req.setArea1(e.getAreas().get(0));
                    }

                    req.setAddress(e.getAddress());
                    req.setRentPrice(e.getRentPrice());
                    req.setCondoFee(e.getCondoFee());
                    req.setWaterFee(e.getWaterFee());
                    req.setOtherFee(e.getOtherFee());
                    req.setMgrCompanyName(e.getMgrCompanyName());
                    req.setMgrCompanyTel(e.getMgrCompanyTel());
                    req.setForeignerLiveSts(e.getForeignerLiveSts());
                    req.setPdf(e.getPdf());
                    req.setNote(e.getNote());
                    req.setUpdatedAt(e.getUpdatedAt().format(dtf));

                    return req;
                }
            ).forEach(req -> {
                try {
                    RequestEntity<DataTranRequest> request = RequestEntity
                        .post("http://162.43.9.86/v1/api/data_tran")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(req);
                    ResponseEntity<String> response = restTemplate.exchange(request, String.class);
                } catch (Exception e) {
                    log.error("re id:{}", req.getReId(), e);
                }
            });
            offset += 1;
        }

    }

}