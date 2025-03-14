package dio.com.br.controller;

import dio.com.br.dto.CardDetailsDTO;
import dio.com.br.persistence.entity.CardEntity;
import dio.com.br.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @PostMapping
    public ResponseEntity<CardEntity> create(@RequestBody CardEntity card) {
        return ResponseEntity.ok(cardService.insert(card));
    }

    @PatchMapping("/{cardId}/move/{columnId}")
    public ResponseEntity<Void> moveToColumn(@PathVariable Long cardId, @PathVariable Long columnId) {
        cardService.moveToColumn(columnId, cardId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<CardDetailsDTO>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(cardService.findById(id));
    }
}
