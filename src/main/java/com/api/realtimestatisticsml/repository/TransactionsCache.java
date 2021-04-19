package com.api.realtimestatisticsml.repository;

import com.api.realtimestatisticsml.models.Transaction;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
public class TransactionsCache {

    private Cache<Integer, Transaction> cache = buildCache();

    public Cache<Integer, Transaction> buildCache(){
        return CacheBuilder
                .newBuilder()
                .expireAfterWrite(60, TimeUnit.SECONDS)
                .build();
    }

    public Cache<Integer, Transaction> getCache(){
        return this.cache;
    }

    public void setCache(Cache<Integer, Transaction> newCache){
        this.cache = newCache;
    }

    public void addNewTransaction(Transaction transaction){
        this.cache.put(transaction.hashCode(),transaction);
    }

    public void cleanCache(){
        this.setCache(buildCache());
    }

    public List<Transaction> getAllTransactions(){
        return new ArrayList<>(this.cache.asMap().values());
    }

    public List<Double> getAllAmounts(){
        return this.cache.asMap().values().stream().map(transaction -> transaction.getAmount()).collect(Collectors.toList());
    }

    public long countTransactions(){
        return this.cache.size();
    }
}
