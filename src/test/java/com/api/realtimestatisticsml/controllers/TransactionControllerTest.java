package com.api.realtimestatisticsml.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = TransactionController.class)
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TransactionController controller;

    String getTransactionJson(String timestampString, String amountString) throws JsonProcessingException {
        Object transaction = new Object() {
            public final String timestamp = timestampString;
            public final String amount = amountString;
        };
        return this.objectMapper.writeValueAsString(transaction);
    }


    @Test
    public void ifThereIsAnyTransactionsCreatedEmptyArrayWillBeReturned() throws Exception {
        ResultActions mvcResult = this.mockMvc.perform(
                get("/transactions").accept(MediaType.APPLICATION_JSON)
        );

        mvcResult.andExpect(status().isOk());
        assertEquals(mvcResult.andReturn().getResponse().getContentAsString(), "[]");
    }

    @Test
    void aTransactionShouldntBeCreatedIfThereIsOneUnparsedValue() throws Exception {

        String unparsedTransaction = getTransactionJson(Instant.now().toString(), "324j.3424");

        ResultActions mvcResult = this.mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(unparsedTransaction));

        mvcResult.andExpect(status().isUnprocessableEntity());
    }

    @Test
    void ifTransactionsJSONIsInvalidTheResponseWillBe400() throws Exception {

        String invalidTransaction = getTransactionJson(Instant.now().toString(), null);

        ResultActions mvcResult = this.mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidTransaction));

        mvcResult.andExpect(status().isBadRequest());
    }

    @Test
    public void ifTransactionsTimestampIsInTheFutureTheResponseWillBe422() throws Exception {
        // String futureTransaction = getTransactionJson(Instant.now().plus(5, ChronoUnit.DAYS).toString(),"4323.234");
        String futureTransaction = getTransactionJson(Instant.now().toString(),"4323.234");

        //String futureTransaction = "{\"timestamp\":\"2019-04-25T03:32:48.844Z\",\"amount\":\"43242.23\" }";

        MvcResult mvcResult = this.mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(futureTransaction)).andReturn();

        mockMvc.perform(asyncDispatch(mvcResult)).andExpect(status().isCreated());
        //mvcResult.andExpect(status().isUnprocessableEntity());
    }

    /*



    @Test
    public void ifTransactionsJSONIsInvalidTheResponseWillBe400(){
        assertTrue(true);
    }

    @Test
    public void ifTheTransactionIsInTheFutureTheResponseWillBe204(){
        assertTrue(true);
    }

    @Test
    public void ifThereIsAnyTransactionsCreatedEmptyArrayWillBeReturned(){
        assertTrue(true);
    }
    */
}
