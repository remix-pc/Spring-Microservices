package com.gui.mscreditappraiser.infra.clients;

import com.gui.mscreditappraiser.domain.model.Card;
import com.gui.mscreditappraiser.domain.model.CustomerCard;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "mscards", path = "/cards")
public interface CardsResourceClient {

    @GetMapping(params = "customerId")
    ResponseEntity<List<CustomerCard>> getCardsByCustomer(@RequestParam("customerId") String customerId);

    @GetMapping(params = "income")
    ResponseEntity<List<Card>> getCardsIncomeAteh(@RequestParam("income")Long income);

}
