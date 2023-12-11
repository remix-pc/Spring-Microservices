package com.gui.mscreditappraiser.application;

import com.gui.mscreditappraiser.domain.model.CustomerData;
import com.gui.mscreditappraiser.domain.model.CustomerSituation;
import com.gui.mscreditappraiser.infra.clients.CustomerResourceClient;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditAppraiserService {

    private final CustomerResourceClient customerClient;


    public CustomerSituation obtainCustomerSituation(String customerId){
        //Obter dados cliente - MSCustomers
        //obter cartoes do cliente - MSCards

        ResponseEntity<CustomerData> customerDataResponse = customerClient.customerData(customerId);


        return CustomerSituation.builder()
                .customer(customerDataResponse.getBody())
                .build();
    }

}
