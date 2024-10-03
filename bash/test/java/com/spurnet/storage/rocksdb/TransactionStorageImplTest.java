package com.spurnet.storage.rocksdb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spurnet.model.Transaction;
import com.spurnet.storage.TransactionStorage;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TransactionStorageImpl implements TransactionStorage {
    private final RocksDB transactionDB;
    private final ObjectMapper objectMapper;
    private static final String PENDING_KEY = "pending";

    public TransactionStorageImpl(RocksDBManager dbManager) {
        this.transactionDB = dbManager.getTransactionDB();
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void addTransaction(Transaction tx) throws Exception {
        byte[] txData = objectMapper.writeValueAsBytes(tx);
        transactionDB.put(tx.getTxId().getBytes(), txData);
        // Optionally, maintain a list of pending transactions
        // This is a simplified example
    }

    @Override
    public Optional<Transaction> getTransaction(String txId) throws Exception {
        byte[] data = transactionDB.get(txId.getBytes());
        if (data == null) return Optional.empty();
        Transaction tx = objectMapper.readValue(data, Transaction.class);
        return Optional.of(tx);
    }

    @Override
    public List<Transaction> getPendingTransactions() throws Exception {
        // Simplified: Iterate through all keys and collect transactions
        List<Transaction> pendingTxs = new ArrayList<>();
        try (var iter = transactionDB.newIterator()) {
            iter.seekToFirst();
            while (iter.isValid()) {
                String key = new String(iter.key());
                if (!key.equals(PENDING_KEY)) { // Exclude special keys
                    Transaction tx = objectMapper.readValue(iter.value(), Transaction.class);
                    pendingTxs.add(tx);
                }
                iter.next();
            }
        }
        return pendingTxs;
    }

    @Override
    public void removeTransaction(String txId) throws Exception {
        transactionDB.delete(txId.getBytes());
    }
}
