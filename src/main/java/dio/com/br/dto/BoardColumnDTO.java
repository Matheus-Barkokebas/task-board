package dio.com.br.dto;

import dio.com.br.persistence.entity.BoardColumnKindEnum;

public record BoardColumnDTO(Long id,
        String name,
        BoardColumnKindEnum kind,
        int cardsAmount) {
}
