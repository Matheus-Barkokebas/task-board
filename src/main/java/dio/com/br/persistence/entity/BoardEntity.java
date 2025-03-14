package dio.com.br.persistence.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import dio.com.br.persistence.entity.enums.BoardColumnKindEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_board")
public class BoardEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<BoardColumnEntity> boardColumns = new ArrayList<>();

	public BoardColumnEntity getInitialColumn() {
		return getFilteredColumn(bc -> bc.getKind().equals(BoardColumnKindEnum.INITIAL));
	}

	public BoardColumnEntity getCancelColumn() {
		return getFilteredColumn(bc -> bc.getKind().equals(BoardColumnKindEnum.CANCEL));
	}

	private BoardColumnEntity getFilteredColumn(Predicate<BoardColumnEntity> filter) {
		return boardColumns.stream().filter(filter).findFirst().orElseThrow();
	}
}
