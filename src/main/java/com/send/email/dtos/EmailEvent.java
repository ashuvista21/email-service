package com.send.email.dtos;

import java.time.Instant;
import java.util.Map;

public record EmailEvent(
        String eventId,                // Unique ID (UUID)
        String eventType,              // e.g., USER_REGISTRATION, PASSWORD_RESET
        String recipient,              // Target email address
        String template,           // Email template identifier
        Map<String, Object> variables, // Template placeholders (e.g., {"userName": "Ashutosh"})
        Instant timestamp              // When the event was created
) {}
