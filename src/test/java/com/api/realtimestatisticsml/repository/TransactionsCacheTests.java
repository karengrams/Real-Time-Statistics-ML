package com.api.realtimestatisticsml.repository;

import com.api.realtimestatisticsml.models.Transaction;
import org.junit.Before;
import org.junit.Test;

import java.time.Instant;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TransactionsCacheTests {
    private TransactionsCache transactionsCache;

    @Before
    public void setUp() {
        this.transactionsCache = new TransactionsCache();
    }

    @Test
    public void ifTheCacheIsCleanedTheSizeWillBe0() {
        Transaction transaction = new Transaction(Instant.now(), 420.420);

        transactionsCache.addNewTransaction(transaction);

        transactionsCache.cleanCache();

        assertEquals("0.0", String.valueOf(transactionsCache.countTransactions()));

    }

    @Test
    public void shouldReturnTransactions() {
        Transaction transaction = new Transaction(Instant.now(), 420.420);
        Transaction otherTransaction = new Transaction(Instant.now(), 420.420);

        transactionsCache.addNewTransaction(otherTransaction);
        transactionsCache.addNewTransaction(transaction);

        assertTrue(transactionsCache.getAllTransactions().contains(transaction));
        assertTrue(transactionsCache.getAllTransactions().contains(otherTransaction));

    }

}
