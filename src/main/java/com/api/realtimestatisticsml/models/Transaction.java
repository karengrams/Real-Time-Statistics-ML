package com.api.realtimestatisticsml.models;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;

public class Transaction {
    private final OffsetDateTime timestamp;
    private final Float amount;

    public Transaction(OffsetDateTime timestamp, Float amount) {
        this.timestamp = timestamp;
        this.amount = amount;
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public Float getAmount() {
        return amount;
    }

}
