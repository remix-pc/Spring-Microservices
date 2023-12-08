package com.gui.mscustomer.application;

import com.gui.mscustomer.domain.Customer;
import com.gui.mscustomer.infra.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository _reporisoty;

    @Transactional
    public Customer save(Customer customer){
        return _reporisoty.save(customer);
    }

    public Optional<Customer> getByCustomerId(String customerId){
        return _reporisoty.findByCustomerId(customerId);
    }
}
