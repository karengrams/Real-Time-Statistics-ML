package com.api.realtimestatisticsml.repositories;

import com.api.realtimestatisticsml.models.Transaction;

import java.util.ArrayList;
import java.util.HashMap;

public class TransactionsRepository {

    private ArrayList<Transaction> transactions;
    private HashMap<String,Float> cache;

    private static class TransactionsRepositoryHolder{
        private static final TransactionsRepository INSTANCE = new TransactionsRepository();
    }

    public static TransactionsRepository getInstance(){
        return TransactionsRepositoryHolder.INSTANCE;
    }

    public void addNewTransaction(Transaction transaction){
        this.transactions.add(transaction);
    }

    public ArrayList<Transaction> getTransactions(){
        return this.transactions;
    }
}
