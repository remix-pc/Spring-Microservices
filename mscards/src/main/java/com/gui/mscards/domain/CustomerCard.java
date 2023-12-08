package com.gui.mscards.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Data
public class CustomerCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerId;
    @ManyToOne
    @JoinColumn(name = "id_card")
    private Card card;
    @Column(name = "card_limit")
    private BigDecimal limit;

}
