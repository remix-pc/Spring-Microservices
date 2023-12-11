package com.gui.mscreditappraiser.application;

import com.gui.mscreditappraiser.application.ex.CommunicationMsException;
import com.gui.mscreditappraiser.application.ex.CustomerDataNotFoundException;
import com.gui.mscreditappraiser.domain.model.AssessmentData;
import com.gui.mscreditappraiser.domain.model.CustomerSituation;
import com.gui.mscreditappraiser.domain.model.ReturnAssessmentCustomer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity queryCustomerSituation(@RequestParam("customerId") String customerId){
        try {
            CustomerSituation customerSituation = creditAppraiserService.obtainCustomerSituation(customerId);
            return ResponseEntity.ok(customerSituation);
        } catch (CustomerDataNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (CommunicationMsException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }

    }

    @PostMapping
    public ResponseEntity carryOutAssessment(@RequestBody AssessmentData data){
        try {

            ReturnAssessmentCustomer entity = creditAppraiserService
                    .queryOutAssessment(data.getCustomerId(), data.getIncome());
            return ResponseEntity.ok(entity);
        } catch (CustomerDataNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (CommunicationMsException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }


    }

}
