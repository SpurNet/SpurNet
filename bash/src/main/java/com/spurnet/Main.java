package com.spurnet;

import com.spurnet.cache.BlockCache;
import com.spurnet.model.Block;
import com.spurnet.model.BlockHeader;
import com.spurnet.model.Transaction;
import com.spurnet.storage.BlockStorage;
import com.spurnet.storage.StateStorage;
import com.spurnet.storage.TransactionStorage;
import com.spurnet.storage.rocksdb.BlockStorageImpl;
import com.spurnet.storage.rocksdb.RocksDBManager;
import com.spurnet.storage.rocksdb.StateStorageImpl;
import com.spurnet.storage.rocksdb.TransactionStorageImpl;

import java.util.Arrays;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        try {
            // Initialize RocksDB Manager with separate paths for hot and cold data
            RocksDBManager dbManager = RocksDBManager.getInstance(
                    "data/block_hot.db",
                    "data/block_cold.db",
                    "data/transactions.db",
                    "data/state.db"
            );

            // Initialize Cache
            BlockCache blockCache = new BlockCache();

            // Initialize Storages
            BlockStorage blockStorage = new BlockStorageImpl(dbManager, blockCache);
            TransactionStorage transactionStorage = new TransactionStorageImpl(dbManager);
            StateStorage stateStorage = new StateStorageImpl(dbManager);

            // Create a sample transaction
            Transaction tx = new Transaction("tx123", "Alice", "Bob", 50.0, System.currentTimeMillis());

            // Add transaction to storage
            transactionStorage.addTransaction(tx);
            System.out.println("Transaction added: " + tx);

            // Retrieve transaction
            Optional<Transaction> retrievedTx = transactionStorage.getTransaction("tx123");
            retrievedTx.ifPresent(System.out::println);

            // Create a sample block
            BlockHeader header = new BlockHeader("prevHash123", System.currentTimeMillis(), 100, 12345);
            Block block = new Block(header, Arrays.asList(tx));

            // Save block
            blockStorage.saveBlock(block);
            System.out.println("Block saved: " + block);

            // Retrieve block
            String latestHash = blockStorage.getLatestBlockHash();
            Optional<Block> retrievedBlock = blockStorage.getBlock(latestHash);
            retrievedBlock.ifPresent(System.out::println);

            // Save and retrieve state
            com.spurnet.model.State state = new com.spurnet.model.State();
            state.updateBalance("Alice", 1000.0);
            state.updateBalance("Bob", 500.0);
            stateStorage.saveState("stateKey1", state);
            com.spurnet.model.State retrievedState = stateStorage.getState("stateKey1");
            System.out.println("Retrieved State: " + retrievedState);

            // Close databases
            dbManager.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
