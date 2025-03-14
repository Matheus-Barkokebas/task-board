package dio.com.br.controller;

import dio.com.br.service.BlockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blocks")
@RequiredArgsConstructor
public class BlockController {

    private final BlockService blockService;

    @PostMapping("/block/{cardId}")
    public ResponseEntity<Void> block(@PathVariable Long cardId, @RequestParam String reason) {
        blockService.block(reason, cardId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/unblock/{cardId}")
    public ResponseEntity<Void> unblock(@PathVariable Long cardId, @RequestParam String reason) {
        blockService.unblock(reason, cardId);
        return ResponseEntity.ok().build();
    }
}
