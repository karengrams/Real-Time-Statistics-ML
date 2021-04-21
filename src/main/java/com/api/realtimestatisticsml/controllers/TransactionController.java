package com.api.realtimestatisticsml.controllers;

import com.api.realtimestatisticsml.models.Transaction;
import com.api.realtimestatisticsml.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(@Autowired TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/transactions")
    public ResponseEntity getTransactions() {
        return ResponseEntity.ok(this.transactionService.getTransactions());
    }

    @PostMapping("/transactions")
    public ResponseEntity<Void> newTransaction( @Valid @RequestBody Transaction transaction) {
        return this.transactionService.postTransaction(transaction);
    }

    @DeleteMapping("/transactions")
    public ResponseEntity<Void> deleteTransactions() {
        this.transactionService.cleanCache();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
