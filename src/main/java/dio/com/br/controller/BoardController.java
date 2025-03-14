package dio.com.br.controller;

import dio.com.br.persistence.entity.BoardEntity;
import dio.com.br.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<BoardEntity> create(@RequestBody BoardEntity board) {
        return ResponseEntity.ok(boardService.insert(board));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BoardEntity> delete(@PathVariable Long id) {
        return ResponseEntity.ok(boardService.delete(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardEntity> findById(@PathVariable Long id) {
        return ResponseEntity.ok(boardService.findById(id));
    }

    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> exists(@PathVariable Long id) {
        return ResponseEntity.ok(boardService.exists(id));
    }
}
