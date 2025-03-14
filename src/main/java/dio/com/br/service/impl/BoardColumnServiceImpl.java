package dio.com.br.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dio.com.br.dto.BoardColumnDTO;
import dio.com.br.persistence.entity.BoardColumnEntity;
import dio.com.br.persistence.entity.enums.BoardColumnKindEnum;
import dio.com.br.persistence.repository.BoardColumnRepository;
import dio.com.br.service.BoardColumnService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BoardColumnServiceImpl implements BoardColumnService {

    private final BoardColumnRepository boardColumnRepository;

    @Override
    public BoardColumnEntity findById(Long id) {
        return boardColumnRepository.findById(id).orElseThrow(() -> new RuntimeException("BoardColumn not found"));
    }

    @Override
    public List<BoardColumnEntity> findAll() {
        return boardColumnRepository.findAll();
    }

    @Override
    public List<BoardColumnEntity> findByBoardId(Long boardId) {
        return boardColumnRepository.findByBoardId(boardId);
    }

    @Override
    public List<BoardColumnDTO> findByBoardIdWithDetails(Long boardId) {
        List<BoardColumnEntity> columns = boardColumnRepository.findByBoardId(boardId);
        return columns.stream().map(column -> new BoardColumnDTO(
                column.getId(),
                column.getName(),
                column.getKind(),
                column.getCards().size()
        )).collect(Collectors.toList());
    }

    @Override
    public BoardColumnKindEnum findByName(String name) {
        return BoardColumnKindEnum.valueOf(name);
    }

    @Override
    public BoardColumnEntity insert(BoardColumnEntity boardColumnToCreate) {
        return boardColumnRepository.save(boardColumnToCreate);
    }

}
