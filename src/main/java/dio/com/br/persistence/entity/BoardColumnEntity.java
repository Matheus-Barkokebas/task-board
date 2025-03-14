package dio.com.br.persistence.entity;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsExclude;
import org.apache.commons.lang3.builder.ToStringExclude;

import dio.com.br.persistence.entity.enums.BoardColumnKindEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_board_column")
public class BoardColumnEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "order")
	private int order;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "kind")
	private BoardColumnKindEnum kind;
	
	@ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
	private BoardEntity board = new BoardEntity();
	
	@OneToMany(mappedBy = "boardColumn", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<CardEntity> cards = new ArrayList<>();
}
