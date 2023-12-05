package com.solvd.realestate.interfaces;

import com.solvd.realestate.transactions.Transaction;

@FunctionalInterface
public interface TransactionHandler<T> {
    void handleTransaction(T transaction);
}