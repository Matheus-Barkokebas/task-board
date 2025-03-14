package dio.com.br.persistence.dao;

import static dio.com.br.persistence.converter.OffsetDateTimeConverter.toOffsetDateTime;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;

import dio.com.br.dto.CardDetailsDTO;
import dio.com.br.persistence.entity.CardEntity;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CardDAO {

    private final Connection connection;

    public CardEntity insert(final CardEntity entity) throws SQLException {
        var sql = "INSERT INTO CARDS (title, description, board_column_id) VALUES (?, ?, ?);";
        try (var statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            var i = 1;
            statement.setString(i++, entity.getTitle());
            statement.setString(i++, entity.getDescription());
            statement.setLong(i, entity.getBoardColumn().getId());
            statement.executeUpdate();
            
            try (var generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entity.setId(generatedKeys.getLong(1));
                }
            }
        }
        return entity;
    }

    public void moveToColumn(final Long columnId, final Long cardId) throws SQLException {
        var sql = "UPDATE CARDS SET board_column_id = ? WHERE id = ?;";
        try (var statement = connection.prepareStatement(sql)) {
            var i = 1;
            statement.setLong(i++, columnId);
            statement.setLong(i, cardId);
            statement.executeUpdate();
        }
    }

    public Optional<CardDetailsDTO> findById(final Long id) throws SQLException {
        var sql =
                """
                SELECT c.id,
                       c.title,
                       c.description,
                       b.blocked_at,
                       b.block_reason,
                       c.board_column_id,
                       bc.name,
                       (SELECT COUNT(sub_b.id)
                          FROM BLOCKS sub_b
                         WHERE sub_b.card_id = c.id) blocks_amount
                  FROM CARDS c
                  LEFT JOIN BLOCKS b
                    ON c.id = b.card_id
                   AND b.unblocked_at IS NULL
                 INNER JOIN BOARDS_COLUMNS bc
                    ON bc.id = c.board_column_id
                  WHERE c.id = ?;
                """;
        try (var statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeQuery();
            var resultSet = statement.getResultSet();
            if (resultSet.next()) {
                var dto = new CardDetailsDTO(
                        resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        Objects.nonNull(resultSet.getString("block_reason")),
                        toOffsetDateTime(resultSet.getTimestamp("blocked_at")),
                        resultSet.getString("block_reason"),
                        resultSet.getInt("blocks_amount"),
                        resultSet.getLong("board_column_id"),
                        resultSet.getString("name")
                );
                return Optional.of(dto);
            }
        }
        return Optional.empty();
    }
}
