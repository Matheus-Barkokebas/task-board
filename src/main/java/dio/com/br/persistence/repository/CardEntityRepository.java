package dio.com.br.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dio.com.br.dto.CardDetailsDTO;
import dio.com.br.persistence.entity.CardEntity;

@Repository
public interface CardEntityRepository extends JpaRepository<CardEntity, Long> {

	@Query("""
			SELECT new dio.com.br.dto.CardDetailsDTO(
			    c.id, c.title, c.description,
			    CASE WHEN b.blockReason IS NOT NULL THEN TRUE ELSE FALSE END,
			    b.blockedAt, b.blockReason,
			    (SELECT COUNT(subB.id) FROM BlockEntity subB WHERE subB.card.id = c.id),
			    c.boardColumn.id, bc.name
			)
			FROM CardEntity c
			LEFT JOIN BlockEntity b ON c.id = b.card.id AND b.unblockedAt IS NULL
			INNER JOIN BoardColumnEntity bc ON bc.id = c.boardColumn.id
			WHERE c.id = :id
			""")
	
	Optional<CardDetailsDTO> findCardDetailsById(Long id);
}
