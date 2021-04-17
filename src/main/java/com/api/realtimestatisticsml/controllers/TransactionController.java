package com.api.realtimestatisticsml.controllers;

import com.api.realtimestatisticsml.models.Transaction;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.ArrayList;

@RestController
public class TransactionController {

    private ArrayList<Transaction> transactions = new ArrayList<Transaction>();

    public void addNewTransaction(Transaction transaction){
        this.transactions.add(transaction);
    }

    @GetMapping("/transactions")
    public ArrayList<Transaction> getTransactions(){
        return this.transactions;
    }

    @PostMapping("/transactions")
    public ResponseEntity newTransaction(@RequestParam(value="timestamp") String timestamp, @RequestParam(value="amount") String amount){
        this.addNewTransaction(new Transaction(OffsetDateTime.parse(timestamp), Float.parseFloat(amount)));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/transactions")
    public void deleteTransactions(){
        this.transactions= new ArrayList<Transaction>();
    }
}
