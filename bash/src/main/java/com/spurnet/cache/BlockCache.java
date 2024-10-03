package com.spurnet.cache;

import com.spurnet.model.Block;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

public class BlockCache {
    private final Cache<String, Block> cache;

    public BlockCache() {
        this.cache = Caffeine.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build();
    }

    public Block get(String blockHash) {
        return cache.getIfPresent(blockHash);
    }

    public void put(String blockHash, Block block) {
        cache.put(blockHash, block);
    }

    public void invalidate(String blockHash) {
        cache.invalidate(blockHash);
    }

    public void invalidateAll() {
        cache.invalidateAll();
    }
}
