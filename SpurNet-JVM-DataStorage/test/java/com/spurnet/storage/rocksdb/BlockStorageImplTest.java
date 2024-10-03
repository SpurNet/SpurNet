package com.spurnet.storage.rocksdb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spurnet.model.Block;
import com.spurnet.storage.BlockStorage;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;

import java.util.Optional;

public class BlockStorageImpl implements BlockStorage {
    private final RocksDB blockDB;
    private final ObjectMapper objectMapper;

    public BlockStorageImpl(RocksDBManager dbManager) {
        this.blockDB = dbManager.getBlockDB();
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void saveBlock(Block block) throws Exception {
        String blockHash = calculateBlockHash(block);
        byte[] blockData = objectMapper.writeValueAsBytes(block);
        blockDB.put(blockHash.getBytes(), blockData);
        // Update the latest block hash
        blockDB.put("latest".getBytes(), blockHash.getBytes());
    }

    @Override
    public Optional<Block> getBlock(String blockHash) throws Exception {
        byte[] data = blockDB.get(blockHash.getBytes());
        if (data == null) return Optional.empty();
        Block block = objectMapper.readValue(data, Block.class);
        return Optional.of(block);
    }

    @Override
    public String getLatestBlockHash() throws Exception {
        byte[] data = blockDB.get("latest".getBytes());
        if (data == null) return null;
        return new String(data);
    }

    private String calculateBlockHash(Block block) {
        // Placeholder for actual hash calculation (e.g., SHA-256)
        return String.valueOf(block.hashCode());
    }
}
