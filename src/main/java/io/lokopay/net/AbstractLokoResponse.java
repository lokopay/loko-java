package io.lokopay.net;

import static java.util.Objects.requireNonNull;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.NonFinal;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Accessors(fluent = true)
abstract class AbstractLokoResponse<T> {
    int code;

    HttpHeaders headers;

    T body;

    public final int code() {
        return this.code;
    }

    public final HttpHeaders headers() {
        return this.headers;
    }

    public final T body() {
        return this.body;
    }

    @NonFinal
    @Getter(AccessLevel.PACKAGE)
    @Setter(AccessLevel.PACKAGE)
    int numRetries;

    public Instant date() {
        Optional<String> dateStr = this.headers.firstValue("Date");
        if (!dateStr.isPresent()) {
            return null;
        }

        return ZonedDateTime.parse(dateStr.get(), DateTimeFormatter.RFC_1123_DATE_TIME).toInstant();
    }

    public String idempotencyKey() {
        return this.headers.firstValue("Idempotency-Key").orElse(null);
    }

    public String requestId() {
        return this.headers.firstValue("Request-Id").orElse(null);
    }

    protected AbstractLokoResponse(int code, HttpHeaders headers, T body) {
        requireNonNull(headers);
        requireNonNull(body);

        this.code = code;
        this.headers = headers;
        this.body = body;
    }
}
