package dio.com.br.service.impl;

import org.springframework.stereotype.Service;

import dio.com.br.persistence.entity.BoardEntity;
import dio.com.br.persistence.repository.BoardEntityRepository;
import dio.com.br.service.BoardService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardEntityRepository boardRepository;

    @Override
    public BoardEntity insert(BoardEntity entity) {
        return boardRepository.save(entity);
    }

    @Override
    public BoardEntity delete(Long id) {
        BoardEntity board = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Board not found"));
        boardRepository.delete(board);
        return board;
    }

    @Override
    public BoardEntity findById(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Board not found"));
    }

    @Override
    public boolean exists(Long id) {
        return boardRepository.existsById(id);
    }
}