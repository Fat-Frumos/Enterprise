package com.enterprise.rental.security;

import java.time.LocalDateTime;
import java.util.UUID;

public class Token {
    private final long uuid;
    private final LocalDateTime expired;

    public Token(long time) {
        uuid = UUID.randomUUID().getMostSignificantBits() & Integer.MAX_VALUE;
        expired = LocalDateTime.now().plusSeconds(time);
    }

    public long getUuid() {
        return uuid;
    }

    public LocalDateTime getExpired() {
        return expired;
    }
}
