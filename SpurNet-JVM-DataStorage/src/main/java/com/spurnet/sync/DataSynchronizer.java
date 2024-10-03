package com.spurnet.sync;

import com.spurnet.model.Block;
import com.spurnet.storage.BlockStorage;

public class DataSynchronizer {
    private final BlockStorage blockStorage;

    public DataSynchronizer(BlockStorage blockStorage) {
        this.blockStorage = blockStorage;
    }

    public void synchronize(Block newBlock) throws Exception {
        String latestHash = blockStorage.getLatestBlockHash();
        if (latestHash == null || isValidNewBlock(newBlock, latestHash)) {
            blockStorage.saveBlock(newBlock);
            // Additional synchronization logic (e.g., notifying other nodes)
        } else {
            throw new Exception("Invalid block received");
        }
    }

    private boolean isValidNewBlock(Block newBlock, String previousHash) {
        // Simplified validation: Check if previousHash matches
        return newBlock.getHeader().getPreviousHash().equals(previousHash);
    }
}
