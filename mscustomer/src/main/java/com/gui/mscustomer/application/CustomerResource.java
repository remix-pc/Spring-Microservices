package com.gui.mscustomer.application;

import com.gui.mscustomer.application.representation.CustomerSaveRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("customers")
@RequiredArgsConstructor
@Slf4j
public class CustomerResource {

    private final CustomerService service;

    @GetMapping
    public String status(){
        log.info("Obtendo o status do microservide de clientes");
        return "Ok";
    }
    @PostMapping
    public ResponseEntity save(@RequestBody CustomerSaveRequest request){
        var customer = request.toModel();
        service.save(customer);
        URI headerLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                .query("customerId={customerId}")
                .buildAndExpand(customer.getCustomerId())
                .toUri();

        return ResponseEntity.created(headerLocation).build();
    }

    @GetMapping(params = "customerId")
    public ResponseEntity customerData(@RequestParam("customerId") String cpf){
        var customer = service.getByCustomerId(cpf);

        if (customer.isEmpty()) {
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(customer);
        }
    }

}