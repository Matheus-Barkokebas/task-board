package dio.com.br.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dio.com.br.persistence.entity.BoardColumnEntity;

@Repository
public interface BoardColumnRepository extends JpaRepository<BoardColumnEntity, Long>{

	List<BoardColumnEntity> findByBoardId(Long boardId);

}
