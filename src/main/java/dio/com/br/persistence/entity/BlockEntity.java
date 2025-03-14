package dio.com.br.persistence.entity;

import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_block")
public class BlockEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "blockedAt")
	private OffsetDateTime blockedAt;
	
	@Column(name = "blockReason")
	private String blockReason;
	
	@Column(name = "unblockedAt")
	private OffsetDateTime unblockedAt;
	
	@Column(name = "unblockReason")
	private String unblockReason;

	@ManyToOne
	@JoinColumn(name = "card_id", nullable = false)
	private CardEntity card;
}
