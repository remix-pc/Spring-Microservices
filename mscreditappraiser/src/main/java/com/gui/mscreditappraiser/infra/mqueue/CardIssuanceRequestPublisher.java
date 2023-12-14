package com.gui.mscreditappraiser.infra.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gui.mscreditappraiser.domain.model.IssuanceRequestCardData;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CardIssuanceRequestPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queueIssuanceCard;

    public void requestCard(IssuanceRequestCardData data) throws JsonProcessingException {

        var json = convertIntoJson(data);
        rabbitTemplate.convertAndSend(queueIssuanceCard.getName(), json);

    }

    private String convertIntoJson(IssuanceRequestCardData data) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        var json = mapper.writeValueAsString(data);
        return json;
    }
}
