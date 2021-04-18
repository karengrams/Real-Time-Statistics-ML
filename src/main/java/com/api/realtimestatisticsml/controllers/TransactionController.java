package com.api.realtimestatisticsml.controllers;

import com.api.realtimestatisticsml.cache.TransactionsCache;
import com.api.realtimestatisticsml.models.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.temporal.TemporalUnit;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class TransactionController {

    private TransactionsCache transactionsCache;

    public TransactionController(@Autowired TransactionsCache transactionsCache) {
        this.transactionsCache = transactionsCache;
    }

    @GetMapping("/transactions")
    public List<Transaction> getTransactions() {
        return this.transactionsCache.getAllTransactions();
    }

    @PostMapping("/transactions")
    public ResponseEntity<Void> newTransaction(@RequestParam(value = "timestamp") String timestamp, @RequestParam(value = "amount") String amount) {
        OffsetDateTime transactionTimestamp = null;
        Float transactionAmount = null;
        try {
            transactionTimestamp = OffsetDateTime.parse(timestamp);
            transactionAmount = Float.parseFloat(amount);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY); // TODO: Handlear mis propias exceptions
        }

        if(transactionTimestamp.isAfter(OffsetDateTime.now())){
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY); // TODO: Handlear mis propias exceptions
        }

        if(transactionTimestamp.isBefore(OffsetDateTime.now().minusSeconds(60))){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // TODO: Handlear mis propias exceptions
        }

        this.transactionsCache.addNewTransaction(new Transaction(transactionTimestamp,transactionAmount));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/transactions")
    public ResponseEntity<Void> deleteTransactions() {
        this.transactionsCache.cleanCache();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
