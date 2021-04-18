package com.api.realtimestatisticsml.models;

import java.time.OffsetDateTime;

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
