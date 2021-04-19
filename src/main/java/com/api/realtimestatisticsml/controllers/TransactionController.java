package com.api.realtimestatisticsml.controllers;

import com.api.realtimestatisticsml.models.Transaction;

import com.api.realtimestatisticsml.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {

    private TransactionService transactionService;

    public TransactionController(@Autowired TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/transactions")
    public List<Transaction> getTransactions() {
        return this.transactionService.getTransactions();
    }

    @PostMapping("/transactions")
    public ResponseEntity<Void> newTransaction(@RequestParam(value = "timestamp") String timestamp, @RequestParam(value = "amount") String amount) {
        return this.transactionService.postTransaction(timestamp, amount);
    }

    @DeleteMapping("/transactions")
    public ResponseEntity<Void> deleteTransactions() {
        return this.transactionService.cleanCache();
    }
}
