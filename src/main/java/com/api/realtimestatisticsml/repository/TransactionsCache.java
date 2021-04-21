package com.api.realtimestatisticsml.repository;

import com.api.realtimestatisticsml.models.Transaction;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
public class TransactionsCache {

    private Cache<UUID, Transaction> cache = buildCache();

    public Cache<UUID, Transaction> buildCache(){
        return CacheBuilder
                .newBuilder()
                .expireAfterWrite(60, TimeUnit.SECONDS)
                .build();
    }

    public Cache<UUID, Transaction> getCache(){
        return this.cache;
    }

    public void setCache(Cache<UUID, Transaction> newCache){
        this.cache = newCache;
    }

    public void addNewTransaction(Transaction transaction){
        this.cache.put(UUID.randomUUID(),transaction);
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

    public double countTransactions(){
        return this.getAllTransactions().size();
    }
}
