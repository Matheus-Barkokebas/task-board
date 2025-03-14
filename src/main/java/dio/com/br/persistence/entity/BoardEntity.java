package dio.com.br.persistence.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.apache.commons.lang3.builder.EqualsExclude;
import org.apache.commons.lang3.builder.ToStringExclude;

import lombok.Data;

import static dio.com.br.persistence.entity.BoardColumnKindEnum.CANCEL;
import static dio.com.br.persistence.entity.BoardColumnKindEnum.INITIAL;

@Data
public class BoardEntity {

	private Long id;
	private String name;
	
	@ToStringExclude
	@EqualsExclude
	private List<BoardColumnEntity> boardColumns = new ArrayList<>();
	
	public BoardColumnEntity getInitialColumn(){
        return getFilteredColumn(bc -> bc.getKind().equals(INITIAL));
    }

    public BoardColumnEntity getCancelColumn(){
        return getFilteredColumn(bc -> bc.getKind().equals(CANCEL));
    }

    private BoardColumnEntity getFilteredColumn(Predicate<BoardColumnEntity> filter){
        return boardColumns.stream()
                .filter(filter)
                .findFirst().orElseThrow();
    }
}
