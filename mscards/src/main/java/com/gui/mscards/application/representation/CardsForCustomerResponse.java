package com.gui.mscards.application.representation;

import com.gui.mscards.domain.CustomerCard;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardsForCustomerResponse {
    private String name;
    private String flag;
    @Column(name = "card_limit")
    private BigDecimal limit;

    public  static CardsForCustomerResponse fromModel(CustomerCard model){
        return new CardsForCustomerResponse(
                model.getCard().getName(),
                model.getCard().getFlag().toString(),
                model.getLimit()
        );
    }
}
