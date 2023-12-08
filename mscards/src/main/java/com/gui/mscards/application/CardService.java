package com.gui.mscards.application;

import com.gui.mscards.domain.Card;
import com.gui.mscards.infra.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository _repository;

    @Transactional
    public Card save(Card card){
        return _repository.save(card);
    }

    public List<Card> getCardsLessThanEqualIncome(Long income){
        var incomeBigDecimal = BigDecimal.valueOf(income);
        return _repository.findByIncomeLessThanEqual(incomeBigDecimal);

    }

}
