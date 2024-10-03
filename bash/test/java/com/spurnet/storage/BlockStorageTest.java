package com.spurnet.storage;

import com.spurnet.model.Block;

import java.util.Optional;

public interface BlockStorage {
    void saveBlock(Block block) throws Exception;
    Optional<Block> getBlock(String blockHash) throws Exception;
    String getLatestBlockHash() throws Exception;
}
