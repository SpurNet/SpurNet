package com.spurnet.model;

import java.util.List;
import java.util.Objects;

public class Block {
    private BlockHeader header;
    private List<Transaction> transactions;

    public Block() {}

    public Block(BlockHeader header, List<Transaction> transactions) {
        this.header = header;
        this.transactions = transactions;
    }

    // Getters and Setters

    public BlockHeader getHeader() {
        return header;
    }

    public void setHeader(BlockHeader header) {
        this.header = header;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Block block = (Block) o;

        return Objects.equals(header, block.header) &&
                Objects.equals(transactions, block.transactions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(header, transactions);
    }

    @Override
    public String toString() {
        return "Block{" +
                "header=" + header +
                ", transactions=" + transactions +
                '}';
    }
}
