package com.gui.mscustomer.application.representation;

import com.gui.mscustomer.domain.Customer;
import lombok.Data;

@Data
public class CustomerSaveRequest {

    private String customerId;
    private String name;
    private Integer age;

    public Customer toModel(){
        return new Customer(customerId, name, age);
    }
}
