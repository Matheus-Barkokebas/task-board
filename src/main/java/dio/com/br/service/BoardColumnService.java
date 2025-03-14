package dio.com.br.service;

import java.util.List;

import dio.com.br.dto.BoardColumnDTO;
import dio.com.br.persistence.entity.BoardColumnEntity;
import dio.com.br.persistence.entity.enums.BoardColumnKindEnum;

public interface BoardColumnService {

	BoardColumnEntity findById(Long id);
	
	List<BoardColumnEntity> findAll();
	
	List<BoardColumnEntity> findByBoardId(Long boardId);
	
	List<BoardColumnDTO> findByBoardIdWithDetails(Long boardId);
	
	BoardColumnKindEnum findByName(String name);
	
	BoardColumnEntity insert(BoardColumnEntity BoardColumnToCreate);
	
}
