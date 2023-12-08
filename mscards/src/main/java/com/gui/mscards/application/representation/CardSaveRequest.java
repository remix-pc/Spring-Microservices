package com.gui.mscards.application.representation;

import com.gui.mscards.domain.Card;
import com.gui.mscards.domain.FlagCard;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardSaveRequest {
    private String name;
    private FlagCard flag;
    private BigDecimal income;
    private BigDecimal basicLimit;

    public Card toModel(){
        return new Card(name, flag, income, basicLimit);
    }
}
