package com.gui.mscreditappraiser.infra.clients;

import com.gui.mscreditappraiser.domain.model.CustomerData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "mscustomers", path = "/customers")
public interface CustomerResourceClient {

    @GetMapping(params = "customerId")
    ResponseEntity<CustomerData> customerData(@RequestParam("customerId") String customerId);

}
