package com.api.realtimestatisticsml.models;

import java.time.Instant;
import java.time.OffsetDateTime;

public class Transaction {
    private final Instant timestamp;
    private final Float amount;

    public Transaction(Instant timestamp, Float amount) {
        this.timestamp = timestamp;
        this.amount = amount;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public Float getAmount() {
        return amount;
    }

}
