package com.solvd.realestate.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConnectionTask extends Thread {
    private final ConnectionPool connectionPool;

    public ConnectionTask(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public void run() {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Thread " + Thread.currentThread().getId() + " got connection: " + connection);

        connection.work();

        connectionPool.releaseConnection(connection);
        System.out.println("Thread " + Thread.currentThread().getId() + " released connection: " + connection);

    }

    public static void main(String args[]) throws InterruptedException {
        int poolSize = 5;
        ConnectionPool connectionPool = ConnectionPool.getInstance(poolSize);
        //
//        ExecutorService executorService = Executors.newFixedThreadPool(7);
//        for (int i = 0; i < 7; i++) {
//            executorService.submit(new ConnectionTask(connectionPool));
//        }
//        executorService.shutdown();
        List<Thread> threads = new ArrayList<>();
        for (int i = 1; i < 8; i++) {
            Thread thread = new Thread(new ConnectionTask(connectionPool));
            thread.start();
            threads.add(thread);
        }
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
}