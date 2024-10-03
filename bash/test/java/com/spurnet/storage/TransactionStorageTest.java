package com.spurnet.storage;

import com.spurnet.model.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionStorage {
    void addTransaction(Transaction tx) throws Exception;
    Optional<Transaction> getTransaction(String txId) throws Exception;
    List<Transaction> getPendingTransactions() throws Exception;
    void removeTransaction(String txId) throws Exception;
}
