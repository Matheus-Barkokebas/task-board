package dio.com.br.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dio.com.br.persistence.entity.BoardEntity;

@Repository
public interface BoardEntityRepository extends JpaRepository<BoardEntity, Long>{

}
