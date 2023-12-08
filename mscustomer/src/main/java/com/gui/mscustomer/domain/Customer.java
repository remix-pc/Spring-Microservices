package com.gui.mscustomer.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Customer{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String customerId;
    @Column
    private String name;
    @Column
    private Integer age;

    public Customer(String customerId, String name, Integer age) {
        this.customerId = customerId;
        this.name = name;
        this.age = age;
    }
}
