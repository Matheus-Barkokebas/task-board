package dio.com.br.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dio.com.br.persistence.entity.BlockEntity;

@Repository
public interface BlockRepository extends JpaRepository<BlockEntity, Long>{

	 Optional<BlockEntity> findByCardIdAndUnblockedAtIsNull(Long cardId);
	
}
