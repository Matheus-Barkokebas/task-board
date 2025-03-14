package dio.com.br.service;

import dio.com.br.persistence.entity.BoardEntity;

public interface BoardService {

	BoardEntity insert(BoardEntity entity);
	
	BoardEntity delete(Long id);
	
	BoardEntity findById(final Long id);
	
	boolean exists(Long id);
}
