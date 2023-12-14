package com.gui.mscreditappraiser.domain.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class IssuanceRequestCardData {
    private Long idCard;
    private String cpf;
    private String address;
    private BigDecimal releasedLimit;

}
