package com.api.realtimestatisticsml.controllers;

import com.api.realtimestatisticsml.repository.TransactionsCache;
import com.api.realtimestatisticsml.models.Statistic;
import com.api.realtimestatisticsml.utils.StatisticsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatisticController {

    private TransactionsCache transactionsCache;

    public StatisticController(@Autowired TransactionsCache transactionsCache) {
        this.transactionsCache = transactionsCache;
    }

    @GetMapping("/statistics")
    public Statistic statistics() {
        return new Statistic(
                StatisticsUtils.getSum(this.transactionsCache.getAllAmounts()),
                StatisticsUtils.getMax(this.transactionsCache.getAllAmounts()),
                StatisticsUtils.getMin(this.transactionsCache.getAllAmounts()),
                StatisticsUtils.getAvg(this.transactionsCache.getAllAmounts()),
                transactionsCache.countTransactions(),
                StatisticsUtils.getPercentile(this.transactionsCache.getAllAmounts())
        );
    }

}
