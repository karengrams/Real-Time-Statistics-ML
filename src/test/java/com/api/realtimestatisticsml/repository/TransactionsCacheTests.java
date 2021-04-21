package com.api.realtimestatisticsml.repository;

import com.api.realtimestatisticsml.models.Transaction;
import org.junit.Before;
import org.junit.Test;

import java.time.Instant;
import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.assertEquals;

public class TransactionsCacheTests {
    public final CountDownLatch latch = new CountDownLatch(1);
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

}
