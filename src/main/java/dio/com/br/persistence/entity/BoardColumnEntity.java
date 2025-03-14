package dio.com.br.persistence.entity;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsExclude;
import org.apache.commons.lang3.builder.ToStringExclude;

import dio.com.br.persistence.entity.enums.BoardColumnKindEnum;
import lombok.Data;

@Data
public class BoardColumnEntity {

	private Long id;
	private String name;
	private int order;
	private BoardColumnKindEnum kind;
	private BoardEntity board = new BoardEntity();
	
	@ToStringExclude
	@EqualsExclude
	private List<CardEntity> cards = new ArrayList<>();
}
