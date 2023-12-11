package com.gui.mscreditappraiser.application;

import com.gui.mscreditappraiser.domain.model.CustomerSituation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/credit-appraiser")
@RequiredArgsConstructor
public class CreditAppraiserController {

    private final  CreditAppraiserService creditAppraiserService;

    @GetMapping
    public String status(){
        return "ok";
    }


    @GetMapping(value = "customer-situation", params = "customerId")
    public ResponseEntity<CustomerSituation> queryCustomerSituation(@RequestParam("customerId") String customerId){
        CustomerSituation customerSituation = creditAppraiserService.obtainCustomerSituation(customerId);
        return ResponseEntity.ok(customerSituation);
    }

}
