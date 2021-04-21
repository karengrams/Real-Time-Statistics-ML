package com.api.realtimestatisticsml.services;

import com.api.realtimestatisticsml.models.Statistic;
import com.api.realtimestatisticsml.repository.TransactionsCache;
import com.api.realtimestatisticsml.utils.StatisticsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticService {

    private final TransactionsCache transactionsCache;

    public StatisticService(@Autowired TransactionsCache transactionsCache) {
        this.transactionsCache = transactionsCache;
    }

    public Statistic getStatistic(){
        return new Statistic(
                StatisticsUtils.getSum(this.transactionsCache.getAllAmounts()),
                StatisticsUtils.getMax(this.transactionsCache.getAllAmounts()),
                StatisticsUtils.getMin(this.transactionsCache.getAllAmounts()),
                StatisticsUtils.getAvg(this.transactionsCache.getAllAmounts()),
                transactionsCache.countTransactions(),
                StatisticsUtils.getP90(this.transactionsCache.getAllAmounts())
        );
    }

}
