package com.spurnet.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class State {
    private Map<String, Double> accountBalances;

    public State() {
        this.accountBalances = new ConcurrentHashMap<>();
    }

    public State(Map<String, Double> accountBalances) {
        this.accountBalances = new ConcurrentHashMap<>(accountBalances);
    }

    // Getters and Setters

    public Map<String, Double> getAccountBalances() {
        return accountBalances;
    }

    public void setAccountBalances(Map<String, Double> accountBalances) {
        this.accountBalances = new ConcurrentHashMap<>(accountBalances);
    }

    public void updateBalance(String account, double amount) {
        this.accountBalances.put(account, amount);
    }

    @Override
    public String toString() {
        return "State{" +
                "accountBalances=" + accountBalances +
                '}';
    }
}
