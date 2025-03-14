package dio.com.br.dto;

import dio.com.br.persistence.entity.enums.BoardColumnKindEnum;

public record BoardColumnInfoDTO(Long id, int order, BoardColumnKindEnum kind) {
}
