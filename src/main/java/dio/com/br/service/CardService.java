package dio.com.br.service;

import java.util.Optional;

import dio.com.br.dto.CardDetailsDTO;
import dio.com.br.persistence.entity.CardEntity;

public interface CardService {

    CardEntity insert(CardEntity cardEntity);

    void moveToColumn(Long columnId, Long cardId);

    Optional<CardDetailsDTO> findById(Long id);
}
