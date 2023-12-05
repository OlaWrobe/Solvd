package com.solvd.realestate.interfaces;

@FunctionalInterface
public interface TransactionHandler<T> {
    void handleTransaction(T transaction);
}