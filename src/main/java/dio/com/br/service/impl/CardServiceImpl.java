package dio.com.br.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import dio.com.br.dto.CardDetailsDTO;
import dio.com.br.persistence.entity.CardEntity;
import dio.com.br.persistence.repository.CardEntityRepository;
import dio.com.br.service.CardService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardEntityRepository cardRepository;

    @Override
    public CardEntity insert(CardEntity entity) {
        return cardRepository.save(entity);
    }

    @Override
    public void moveToColumn(Long columnId, Long cardId) {
        cardRepository.findById(cardId).ifPresent(card -> {
            card.getBoardColumn().setId(columnId);
            cardRepository.save(card);
        });
    }

    @Override
    public Optional<CardDetailsDTO> findById(Long id) {
        return cardRepository.findCardDetailsById(id);
    }
}
