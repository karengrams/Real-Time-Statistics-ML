package com.api.realtimestatisticsml.controllers;

import com.api.realtimestatisticsml.cache.TransactionsCache;
import com.api.realtimestatisticsml.models.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;

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
        this.transactionsCache.addNewTransaction(new Transaction(OffsetDateTime.parse(timestamp), Float.parseFloat(amount)));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/transactions")
    public void deleteTransactions() {
        this.transactionsCache.cleanCache();
    }
}
