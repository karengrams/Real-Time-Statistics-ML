package com.api.realtimestatisticsml.services;

import com.api.realtimestatisticsml.models.Transaction;
import com.api.realtimestatisticsml.repository.TransactionsCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionsCache transactionsCache;

    public TransactionService(@Autowired TransactionsCache transactionsCache) {
        this.transactionsCache = transactionsCache;
    }

    public ResponseEntity<Void> postTransaction(Transaction transaction) {

        if (transaction.getTimestamp().isAfter(Instant.now())) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        if (transaction.getTimestamp().isBefore(Instant.now().minusSeconds(60))) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        this.transactionsCache.addNewTransaction(transaction);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public List<Transaction> getTransactions() {
        return this.transactionsCache.getAllTransactions();
    }

    public void cleanCache() {
        this.transactionsCache.cleanCache();
    }

}
