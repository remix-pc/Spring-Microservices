package com.gui.mscards.application;

import com.gui.mscards.application.representation.CardSaveRequest;
import com.gui.mscards.application.representation.CardsForCustomerResponse;
import com.gui.mscards.domain.Card;
import com.gui.mscards.domain.CustomerCard;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cards")
@RequiredArgsConstructor
public class CardsResource {

    private final CardService cardService;
    private final CustomerCardService customerCardService;


    @GetMapping
    public String status(){
        return "ok";
    }

    @PostMapping
    public ResponseEntity register(@RequestBody CardSaveRequest request){
        Card card = request.toModel();
        cardService.save(card);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }
    @GetMapping(params = "income")
    public ResponseEntity<List<Card>> getCardsIncomeAteh(@RequestParam("income")Long income){
        List<Card> listCards = cardService.getCardsLessThanEqualIncome(income);
        return ResponseEntity.ok(listCards);
    }


    @GetMapping(params = "customerId")
    public ResponseEntity<List<CardsForCustomerResponse>> getCardsByCustomer(String customerId){
        List<CustomerCard> list = customerCardService.listCardsByCustomerId(customerId);
        List<CardsForCustomerResponse> resultList = list.stream()
                .map(CardsForCustomerResponse::fromModel).collect(Collectors.toList());
        return ResponseEntity.ok(resultList);
    }

}
