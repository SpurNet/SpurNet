package com.spurnet.storage.rocksdb;

import org.rocksdb.RocksDB;
import org.rocksdb.Options;
import org.rocksdb.RocksDBException;

public class RocksDBManager {
    private static RocksDBManager instance;
    private RocksDB blockDB;
    private RocksDB transactionDB;
    private RocksDB stateDB;

    private RocksDBManager(String blockDBPath, String txDBPath, String stateDBPath) throws RocksDBException {
        RocksDB.loadLibrary();
        Options options = new Options().setCreateIfMissing(true);
        this.blockDB = RocksDB.open(options, blockDBPath);
        this.transactionDB = RocksDB.open(options, txDBPath);
        this.stateDB = RocksDB.open(options, stateDBPath);
    }

    public static synchronized RocksDBManager getInstance(String blockDBPath, String txDBPath, String stateDBPath) throws RocksDBException {
        if (instance == null) {
            instance = new RocksDBManager(blockDBPath, txDBPath, stateDBPath);
        }
        return instance;
    }

    public RocksDB getBlockDB() {
        return blockDB;
    }

    public RocksDB getTransactionDB() {
        return transactionDB;
    }

    public RocksDB getStateDB() {
        return stateDB;
    }

    public void close() {
        if (blockDB != null) blockDB.close();
        if (transactionDB != null) transactionDB.close();
        if (stateDB != null) stateDB.close();
    }
}
