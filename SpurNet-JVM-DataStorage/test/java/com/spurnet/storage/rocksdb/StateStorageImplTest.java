package com.spurnet.storage.rocksdb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spurnet.model.State;
import com.spurnet.storage.StateStorage;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;

public class StateStorageImpl implements StateStorage {
    private final RocksDB stateDB;
    private final ObjectMapper objectMapper;

    public StateStorageImpl(RocksDBManager dbManager) {
        this.stateDB = dbManager.getStateDB();
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void saveState(String stateKey, State state) throws Exception {
        byte[] stateData = objectMapper.writeValueAsBytes(state);
        stateDB.put(stateKey.getBytes(), stateData);
    }

    @Override
    public State getState(String stateKey) throws Exception {
        byte[] data = stateDB.get(stateKey.getBytes());
        if (data == null) return null;
        return objectMapper.readValue(data, State.class);
    }
}
