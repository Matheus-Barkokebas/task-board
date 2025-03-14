package dio.com.br.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import dio.com.br.persistence.dao.BoardColumnDAO;
import dio.com.br.persistence.entity.BoardColumnEntity;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BoardColumnQueryService {

    private final Connection connection;

    public Optional<BoardColumnEntity> findById(final Long id) throws SQLException {
        var dao = new BoardColumnDAO(connection);
        return dao.findById(id);
    }

}
