package com.solvd.realestate.threads;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConnectionTaskAsynch implements Runnable {
    private final static Logger LOGGER = LogManager.getLogger(ConnectionTaskAsynch.class);
    private final ConnectionPool connectionPool;

    public ConnectionTaskAsynch(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public void run() {
        CompletionStage<Connection> stage = CompletableFuture.supplyAsync(() -> {
            try {
                return connectionPool.getConnection();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        stage.thenAccept(connection -> {
            LOGGER.info("Thread " + Thread.currentThread().getId() + " got connection: " + connection.getId());

            connection.work();

            connectionPool.releaseConnection(connection);
            LOGGER.info("Thread " + Thread.currentThread().getId() + " released connection: " + connection.getId());
        });
    }

    public static void main(String args[]) throws InterruptedException {
        int poolSize = 5;
        ConnectionPool connectionPool = ConnectionPool.getInstance(poolSize);

        ExecutorService executorService = Executors.newFixedThreadPool(7);
        for (int i = 0; i < 7; i++) {
            executorService.submit(new ConnectionTaskAsynch(connectionPool));
        }
        executorService.shutdown();

    }
}
