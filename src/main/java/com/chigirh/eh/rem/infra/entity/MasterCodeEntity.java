package com.chigirh.eh.rem.infra.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MasterCodeEntity {
    private String codeValue;
    private String name;
    private String codeOrder;
}
