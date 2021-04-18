package com.api.realtimestatisticsml.controllers;

import com.api.realtimestatisticsml.cache.TransactionsCache;
import com.api.realtimestatisticsml.models.Statistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatisticController {

    private TransactionsCache transactionsCache;

    public StatisticController(@Autowired TransactionsCache transactionsCache) {
        this.transactionsCache = transactionsCache;
    }

    public Double getSum(){
        return this.transactionsCache.getAllAmounts().stream().mapToDouble(Float::longValue).sum();
    }

    public Double getMax(){
        return this.transactionsCache.getAllAmounts().stream().mapToDouble(Float::longValue).max().getAsDouble();
    }

    public Double getMin(){
        return this.transactionsCache.getAllAmounts().stream().mapToDouble(Float::longValue).min().getAsDouble();
    }

    public Double getAvg(){
        return this.transactionsCache.getAllAmounts().stream().mapToDouble(Float::longValue).average().getAsDouble();
    }

    @GetMapping("/statistics")
    public Statistic statistics() {
        return new Statistic(getSum(), getMax(), getMin(), getAvg(), transactionsCache.countTransactions());
    }

}
