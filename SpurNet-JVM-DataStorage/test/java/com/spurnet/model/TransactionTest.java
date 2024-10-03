package com.spurnet.model;

import java.util.Objects;

public class Transaction {
    private String txId;
    private String from;
    private String to;
    private double amount;
    private long timestamp;

    public Transaction() {}

    public Transaction(String txId, String from, String to, double amount, long timestamp) {
        this.txId = txId;
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    // Getters and Setters

    public String getTxId() {
        return txId;
    }

    public void setTxId(String txId) {
        this.txId = txId;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;

        return Double.compare(that.amount, amount) == 0 &&
                timestamp == that.timestamp &&
                Objects.equals(txId, that.txId) &&
                Objects.equals(from, that.from) &&
                Objects.equals(to, that.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(txId, from, to, amount, timestamp);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "txId='" + txId + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", amount=" + amount +
                ", timestamp=" + timestamp +
                '}';
    }
}
