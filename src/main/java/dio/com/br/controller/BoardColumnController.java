package dio.com.br.controller;

import dio.com.br.dto.BoardColumnDTO;
import dio.com.br.persistence.entity.BoardColumnEntity;
import dio.com.br.service.BoardColumnService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board-columns")
@AllArgsConstructor
public class BoardColumnController {

    private final BoardColumnService boardColumnService;

    @GetMapping("/{id}")
    public ResponseEntity<BoardColumnEntity> getById(@PathVariable Long id) {
        return ResponseEntity.ok(boardColumnService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<BoardColumnEntity>> getAll() {
        return ResponseEntity.ok(boardColumnService.findAll());
    }

    @GetMapping("/board/{boardId}")
    public ResponseEntity<List<BoardColumnDTO>> getByBoardId(@PathVariable Long boardId) {
        return ResponseEntity.ok(boardColumnService.findByBoardIdWithDetails(boardId));
    }

    @PostMapping
    public ResponseEntity<BoardColumnEntity> create(@RequestBody BoardColumnEntity column) {
        return ResponseEntity.ok(boardColumnService.insert(column));
    }
}
