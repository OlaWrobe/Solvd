package com.solvd.realestate.threads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConnectionTask extends Thread {
    private final static Logger LOGGER = LogManager.getLogger(ConnectionTask.class);
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
        LOGGER.info("Thread " + Thread.currentThread().getId() + " got connection: " + connection.getId());


        connection.work();

        connectionPool.releaseConnection(connection);
        LOGGER.info("Thread " + Thread.currentThread().getId() + " released connection: " + connection.getId());
    }

    public static void main(String args[]) throws InterruptedException {
        int poolSize = 5;
        ConnectionPool connectionPool = ConnectionPool.getInstance(poolSize);

        ExecutorService executorService = Executors.newFixedThreadPool(7);
        for (int i = 0; i < 7; i++) {
            executorService.submit(new ConnectionTask(connectionPool));
        }
        executorService.shutdown();
    }
}