package dio.com.br.service.impl;

import java.time.OffsetDateTime;

import org.springframework.stereotype.Service;

import dio.com.br.persistence.entity.BlockEntity;
import dio.com.br.persistence.repository.BlockRepository;
import dio.com.br.persistence.repository.CardEntityRepository;
import dio.com.br.service.BlockService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BlockServiceImpl implements BlockService {

    private final BlockRepository blockRepository;
    private final CardEntityRepository cardRepository;

    @Override
    public void block(String reason, Long cardId) {
        cardRepository.findById(cardId).ifPresent(card -> {
            BlockEntity block = new BlockEntity();
            block.setBlockedAt(OffsetDateTime.now());
            block.setBlockReason(reason);
            blockRepository.save(block);
        });
    }

    @Override
    public void unblock(String reason, Long cardId) {
        blockRepository.findByCardIdAndUnblockedAtIsNull(cardId).ifPresent(block -> {
            block.setUnblockedAt(OffsetDateTime.now());
            block.setUnblockReason(reason);
            blockRepository.save(block);
        });
    }
}
