package com.api.realtimestatisticsml.controllers;

import com.api.realtimestatisticsml.models.Transaction;
import com.api.realtimestatisticsml.repository.TransactionsCache;
import com.api.realtimestatisticsml.services.TransactionService;
import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class TransactionControllerTest {

    private TransactionsCache cache;
    private TransactionController controller;
    private TransactionService service;

    @Before
    public void setUp() {
        this.cache = Mockito.mock(TransactionsCache.class);
        this.service = new TransactionService(this.cache);
        this.controller = new TransactionController(service);
    }

    @Test
    public void ifTransactionsTimestampIsInTheFutureTheResponseWillBe422() {
        Transaction futureTransaction = new Transaction(Instant.now().plus(5, ChronoUnit.DAYS), 4323.234);
        ResponseEntity<Void> httpResponse = this.controller.newTransaction(futureTransaction);

        Assert.assertEquals(httpResponse.getStatusCode(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @Test
    public void ifATransactionIsCreatedInTheResponseWillBe201() {
        Transaction validTransaction = new Transaction(Instant.now(), 4323.234);
        ResponseEntity<Void> httpResponse = this.controller.newTransaction(validTransaction);

        Assert.assertEquals(httpResponse.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void ifTransactionsTimestampIsInThePastTheResponseWillBe204() {
        Transaction pastTransaction = new Transaction(Instant.now().minus(5, ChronoUnit.DAYS), 4323.234);
        ResponseEntity<Void> httpResponse = this.controller.newTransaction(pastTransaction);

        Assert.assertEquals(httpResponse.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    @Test
    public void ifTheTransactionIsDeletedTheResponseWillBe204() {
        ResponseEntity<Void> httpResponse = this.controller.deleteTransactions();

        Assert.assertEquals(httpResponse.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    @Test
    public void whenGetTransactionsIsCalledItShouldReturnAllTheTransactionsSaved() throws JSONException {
        Transaction validTransaction = new Transaction(Instant.now(), 4323.234);

        this.controller.newTransaction(validTransaction);

        ResponseEntity httpResponse = this.controller.getTransactions();

        JSONArray jsonObject = new JSONArray(httpResponse.getBody().toString());

        Assert.assertEquals(httpResponse.getStatusCode(), HttpStatus.OK);
        Assert.assertFalse(jsonObject.length() > 0);
    }

}
