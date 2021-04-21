package com.api.realtimestatisticsml.controllers;

import com.api.realtimestatisticsml.models.Transaction;
import com.api.realtimestatisticsml.repository.TransactionsCache;
import com.api.realtimestatisticsml.services.StatisticService;
import com.api.realtimestatisticsml.services.TransactionService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;

public class StatisticControllerTests {

    private TransactionsCache cache;
    private StatisticService service;
    private StatisticController controller;

    @Before
    public void setUp() {
        this.cache = new TransactionsCache();
        this.service = new StatisticService(this.cache);
        this.controller = new StatisticController(this.service);
    }

    @Test
    public void shouldReturnStatistics(){
        Transaction validTransaction = new Transaction(Instant.now(), 4323.234);

        this.cache.addNewTransaction(validTransaction);

        ResponseEntity httpResponse = this.controller.statistics();

        Assert.assertEquals(httpResponse.getStatusCode(), HttpStatus.OK);
    }

}
