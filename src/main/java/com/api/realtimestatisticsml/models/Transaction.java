package com.api.realtimestatisticsml.models;

import java.time.Instant;
import java.time.OffsetDateTime;

public class Transaction {
    private final Instant timestamp;
    private final Double amount;

    public Transaction(Instant timestamp, Double amount) {
        this.timestamp = timestamp;
        this.amount = amount;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public Double getAmount() {
        return amount;
    }

}
