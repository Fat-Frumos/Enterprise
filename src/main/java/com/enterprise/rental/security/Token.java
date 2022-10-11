package com.enterprise.rental.security;

import java.time.LocalDateTime;
import java.util.UUID;

public class Token {
    private final String uuid;
    private final LocalDateTime expired;

    public Token(long time) {
        uuid = UUID.randomUUID().toString();
        expired = LocalDateTime.now().plusSeconds(time);
    }

    public String getUuid() {
        return uuid;
    }

    public LocalDateTime getExpired() {
        return expired;
    }
}
