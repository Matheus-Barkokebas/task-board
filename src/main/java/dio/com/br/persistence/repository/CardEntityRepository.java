package dio.com.br.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dio.com.br.dto.CardDetailsDTO;
import dio.com.br.persistence.entity.CardEntity;

@Repository
public interface CardEntityRepository extends JpaRepository<CardEntity, Long> {

    @Query("SELECT c FROM CardEntity c WHERE c.id = :id") 
    Optional<CardDetailsDTO> findCardDetailsById(@Param("id") Long id);
}
