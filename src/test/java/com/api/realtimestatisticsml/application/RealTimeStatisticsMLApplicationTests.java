package com.api.realtimestatisticsml.application;

import com.api.realtimestatisticsml.RealTimeStatisticsMlApplication;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = RealTimeStatisticsMlApplication.class
)
public class RealTimeStatisticsMLApplicationTests {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    String getTransactionJson(String timestampString, String amountString) throws JsonProcessingException {
        Object transaction = new Object() {
            public final String timestamp = timestampString;
            public final String amount = amountString;
        };
        return this.objectMapper.writeValueAsString(transaction);
    }

    @Test
    public void aTransactionShouldntBeCreatedIfThereIsOneUnparsedValue() throws Exception {

        String unparsedTransaction = getTransactionJson(Instant.now().toString(), "324j.3424");

        ResultActions mvcResult = this.mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(unparsedTransaction));

        mvcResult.andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void ifTransactionsJSONIsInvalidTheResponseWillBe400() throws Exception {

        String invalidTransaction = getTransactionJson(Instant.now().toString(), null);

        ResultActions mvcResult = this.mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidTransaction));

        mvcResult.andExpect(status().isBadRequest());
    }

    @Test
    public void checkIfStatisticsHasBeenCreatedWhenTransactionIsCreated() throws Exception {
        String validTransaction = getTransactionJson(Instant.now().toString(), "4323.234");

        ResultActions mvcResult = this.mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validTransaction));

        ResultActions mvcStatistics = this.mockMvc.perform(
                get("/statistics").accept(MediaType.APPLICATION_JSON)
        );

        JSONObject jsonObject = new JSONObject(mvcStatistics.andReturn().getResponse().getContentAsString());

        Assert.assertEquals(4323.234, jsonObject.get("sum"));
        Assert.assertEquals(4323.234, jsonObject.get("max"));
        Assert.assertEquals(4323.234, jsonObject.get("min"));
        Assert.assertEquals(4323.234, jsonObject.get("p90"));
        Assert.assertEquals(4323.234, jsonObject.get("avg"));
        Assert.assertEquals(1.0, jsonObject.get("count"));

    }


}
