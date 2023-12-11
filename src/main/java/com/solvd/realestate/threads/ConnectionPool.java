package com.solvd.realestate.threads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class ConnectionPool {
    private volatile static ConnectionPool instance;
    private final BlockingQueue<Connection> conns;
    private static int poolSize;
    private static int createdCons = 0;

    private ConnectionPool(int poolSize) {
        this.poolSize = poolSize;
        this.conns = new ArrayBlockingQueue<>(poolSize);
    }

    public synchronized static ConnectionPool getInstance(int size) {
        if (instance == null) {
            instance = new ConnectionPool(size);
        }
        return instance;
    }

    public Connection getConnection() throws InterruptedException {
        if (createdCons < poolSize) {
            createConnection();
            return conns.take();
        } else {
            return conns.take();
        }
    }

    public void releaseConnection(Connection con) {
        conns.offer(con);
    }

    private Connection createConnection() throws InterruptedException {
        createdCons++;
        Connection newConnection = new Connection();
        conns.put(newConnection);
        return newConnection;
    }
}
