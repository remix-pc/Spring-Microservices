package com.gui.mscards.application;

import com.gui.mscards.domain.CustomerCard;
import com.gui.mscards.infra.repository.CustomerCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerCardService {

    private final CustomerCardRepository _repository;

    public List<CustomerCard> listCardsByCustomerId(String customerId){
        return _repository.findByCustomerId(customerId);
    }

}
