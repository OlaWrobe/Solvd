package com.solvd.realestate.threads;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class ConnectionTaskAsynch implements Runnable {
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
            System.out.println("Thread " + Thread.currentThread().getId() + " got connection: " + connection);

            connection.work();

            connectionPool.releaseConnection(connection);
            System.out.println("Thread " + Thread.currentThread().getId() + " released connection: " + connection);
        });
    }

    public static void main(String args[]) throws InterruptedException {
        int poolSize = 5;
        ConnectionPool connectionPool = ConnectionPool.getInstance(poolSize);
    }
}
