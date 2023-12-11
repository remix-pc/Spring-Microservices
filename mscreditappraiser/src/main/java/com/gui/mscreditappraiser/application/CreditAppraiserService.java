package com.gui.mscreditappraiser.application;

import com.gui.mscreditappraiser.application.ex.CommunicationMsException;
import com.gui.mscreditappraiser.application.ex.CustomerDataNotFoundException;
import com.gui.mscreditappraiser.domain.model.*;
import com.gui.mscreditappraiser.infra.clients.CardsResourceClient;
import com.gui.mscreditappraiser.infra.clients.CustomerResourceClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreditAppraiserService {

    private final CustomerResourceClient customerClient;
    private final CardsResourceClient cardClient;


    public CustomerSituation obtainCustomerSituation(String customerId) throws
            CustomerDataNotFoundException, CommunicationMsException {
        try{
            ResponseEntity<CustomerData> customerDataResponse = customerClient.customerData(customerId);
            ResponseEntity<List<CustomerCard>> cardsResponse = cardClient.getCardsByCustomer(customerId);

            return CustomerSituation.builder()
                    .customer(customerDataResponse.getBody())
                    .cards(cardsResponse.getBody())
                    .build();
        } catch (FeignException.FeignClientException e) {
            int status = e.status();

            if (HttpStatus.NOT_FOUND.value() == status){
                throw new CustomerDataNotFoundException();
            }
            throw new CommunicationMsException(e.getMessage(), status);
        }
    }


    public ReturnAssessmentCustomer queryOutAssessment(String customerId, Long income) throws
            CustomerDataNotFoundException,
            CommunicationMsException{

        try {
            ResponseEntity<CustomerData> customerDataResponse = customerClient.customerData(customerId);
            ResponseEntity<List<Card>> cardsResponse = cardClient.getCardsIncomeAteh(income);

            List<Card> cards = cardsResponse.getBody();
            var cardListApproveds = cards.stream().map(card -> {

                CustomerData customerData = customerDataResponse.getBody();

                BigDecimal basicLimit = card.getBasicLimit();
                BigDecimal ageBD = BigDecimal.valueOf(customerData.getAge());
                var factor = ageBD.divide(BigDecimal.valueOf(10));

                BigDecimal approvedLimit = factor.multiply(basicLimit);

                ApprovedCard approved = new ApprovedCard();
                approved.setCard(card.getName());
                approved.setFlag(card.getFlg());
                approved.setApprovedLimit(approvedLimit);

                return approved;
            }).collect(Collectors.toList());

            return new ReturnAssessmentCustomer(cardListApproveds);

        }catch (FeignException.FeignClientException e) {
            int status = e.status();

            if (HttpStatus.NOT_FOUND.value() == status){
                throw new CustomerDataNotFoundException();
            }
            throw new CommunicationMsException(e.getMessage(), status);
        }

    }

}
