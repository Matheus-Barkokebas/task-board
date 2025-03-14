package dio.com.br.persistence.converter;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class OffsetDateTimeConverter {

    private static final ZoneOffset UTC = ZoneOffset.UTC;

    public static OffsetDateTime toOffsetDateTime(final Timestamp value) {
        return Objects.nonNull(value) ? OffsetDateTime.ofInstant(value.toInstant(), UTC) : null;
    }

    public static Timestamp toTimestamp(final OffsetDateTime value) {
        return Objects.nonNull(value) ? Timestamp.valueOf(value.atZoneSameInstant(UTC).toLocalDateTime()) : null;
    }
}

