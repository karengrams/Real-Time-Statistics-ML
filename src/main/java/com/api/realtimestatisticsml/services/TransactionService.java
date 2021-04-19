package com.api.realtimestatisticsml.services;

import com.api.realtimestatisticsml.models.Transaction;
import com.api.realtimestatisticsml.repository.TransactionsCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.List;

@Service
public class TransactionService {

    private TransactionsCache transactionsCache;

    public TransactionService(@Autowired TransactionsCache transactionsCache) {
        this.transactionsCache = transactionsCache;
    }

    public ResponseEntity<Void> postTransaction(String timestamp, String amount) {
        Instant transactionTimestamp = null;
        Double transactionAmount = null;

        try {
            transactionTimestamp = Instant.parse(timestamp);
            transactionAmount = Double.parseDouble(amount);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        if (Instant.now().isBefore(transactionTimestamp)) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        if (transactionTimestamp.isBefore(Instant.now().minusSeconds(60))) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        this.transactionsCache.addNewTransaction(new Transaction(transactionTimestamp, transactionAmount));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public List<Transaction> getTransactions() {
        return this.transactionsCache.getAllTransactions();
    }

    public ResponseEntity<Void> cleanCache() {
        this.transactionsCache.cleanCache();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
