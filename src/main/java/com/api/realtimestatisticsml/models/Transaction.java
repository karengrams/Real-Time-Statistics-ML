package com.api.realtimestatisticsml.models;

import javax.validation.constraints.NotNull;
import java.time.Instant;

public class Transaction {

    @NotNull(message = "timestamp cannot be null")
    private Instant timestamp;

    @NotNull(message = "amount cannot be null")
    private Double amount;

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

}
