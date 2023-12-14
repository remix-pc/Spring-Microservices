package com.gui.mscards.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class IssuanceRequestCardData {
    private Long idCard;
    private String cpf;
    private String address;
    private BigDecimal releasedLimit;

}
