package com.gui.mscards.infra.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gui.mscards.domain.Card;
import com.gui.mscards.domain.CustomerCard;
import com.gui.mscards.domain.IssuanceRequestCardData;
import com.gui.mscards.infra.repository.CardRepository;
import com.gui.mscards.infra.repository.CustomerCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CardEmissionSubscriber {


    private final CardRepository cardRepository;
    private final CustomerCardRepository customerCardRepository;

    @RabbitListener(queues = "${mq.queues.emissao-cartoes}")
    public void receiveRequestIssue(@Payload String payload){
        try {
            var mapper = new ObjectMapper();
            System.out.println(payload);
            IssuanceRequestCardData data = mapper.readValue(payload, IssuanceRequestCardData.class);
            Card card = cardRepository.findById(data.getIdCard()).orElseThrow();
            CustomerCard customerCard = new CustomerCard();
            System.out.println(data);
            customerCard.setCard(card);
            customerCard.setCustomerId(data.getCpf());
            customerCard.setLimit(data.getReleasedLimit());

            customerCardRepository.save(customerCard);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
