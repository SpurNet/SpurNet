package com.spurnet.model;

import java.util.Arrays;
import java.util.Objects;

public class BlockHeader {
    private String previousHash;
    private long timestamp;
    private int nonce;
    private int merkleRoot; // Placeholder for Merkle Root representation

    public BlockHeader() {}

    public BlockHeader(String previousHash, long timestamp, int nonce, int merkleRoot) {
        this.previousHash = previousHash;
        this.timestamp = timestamp;
        this.nonce = nonce;
        this.merkleRoot = merkleRoot;
    }

    // Getters and Setters

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getNonce() {
        return nonce;
    }

    public void setNonce(int nonce) {
        this.nonce = nonce;
    }

    public int getMerkleRoot() {
        return merkleRoot;
    }

    public void setMerkleRoot(int merkleRoot) {
        this.merkleRoot = merkleRoot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlockHeader that = (BlockHeader) o;

        return timestamp == that.timestamp &&
                nonce == that.nonce &&
                merkleRoot == that.merkleRoot &&
                Objects.equals(previousHash, that.previousHash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(previousHash, timestamp, nonce, merkleRoot);
    }

    @Override
    public String toString() {
        return "BlockHeader{" +
                "previousHash='" + previousHash + '\'' +
                ", timestamp=" + timestamp +
                ", nonce=" + nonce +
                ", merkleRoot=" + merkleRoot +
                '}';
    }
}
