package com.gui.mscards.infra.repository;

import com.gui.mscards.domain.CustomerCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerCardRepository extends JpaRepository<CustomerCard, Long> {
    List<CustomerCard> findByCustomerId(String customerId);
}
