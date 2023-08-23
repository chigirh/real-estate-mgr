package com.chigirh.eh.fx.infra.model.restapi;

import java.util.List;
import lombok.Data;

@Data
public class RateGetResponse {
    private List<RateGetRecord> quotes;
}
