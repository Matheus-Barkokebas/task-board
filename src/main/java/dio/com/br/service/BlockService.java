package dio.com.br.service;

public interface BlockService {

    void block(String reason, Long cardId);

    void unblock(String reason, Long cardId);
}
