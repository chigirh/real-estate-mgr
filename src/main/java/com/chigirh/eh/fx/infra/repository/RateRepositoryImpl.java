package com.chigirh.eh.fx.infra.repository;

import com.chigirh.eh.fx.domain.model.Rate;
import com.chigirh.eh.fx.domain.repository.RateRepository;
import com.chigirh.eh.fx.infra.model.restapi.RateGetRecord;
import com.chigirh.eh.fx.infra.model.restapi.RateGetResponse;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class RateRepositoryImpl implements RateRepository {

    @Override
    public Rate fetchUsdJpy() {
        var restTemplate = new RestTemplateBuilder().setBufferRequestBody(false).build();

        var converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.TEXT_HTML));
        restTemplate.getMessageConverters().add(converter);


        var url = "https://www.gaitameonline.com/rateaj/getrate";
        var request = RequestEntity.get(url).build();

        var response = restTemplate.exchange(request, RateGetResponse.class);

        return response.getBody().getQuotes()
            .stream().filter(e -> "USDJPY".equals(e.getCurrencyPairCode()))
            .map(this::map).findFirst().get();
    }

    public Rate map(RateGetRecord record) {
        return new Rate(record.getHigh(), record.getOpen(), record.getBid(), record.getCurrencyPairCode(), record.getAsk(), record.getLow());
    }
}
