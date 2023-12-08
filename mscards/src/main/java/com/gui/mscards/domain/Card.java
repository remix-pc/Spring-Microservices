package com.gui.mscards.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private FlagCard flag;
    private BigDecimal income;
    private BigDecimal basicLimit;

    public Card(String name, FlagCard flag, BigDecimal income, BigDecimal basicLimit) {
        this.name = name;
        this.flag = flag;
        this.income = income;
        this.basicLimit = basicLimit;
    }
}
