package com.solvd.realestate.threads;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private volatile static ConnectionPool instance;
    BlockingQueue<Connection> conns;

    private ConnectionPool(int poolSize) throws InterruptedException {
        this.conns = new ArrayBlockingQueue<>(poolSize);

        for (int i = 0; i < poolSize; i++) {
            conns.put(new Connection());
        }
    }

    public synchronized static ConnectionPool getInstance(int size) throws InterruptedException {
        if (instance == null) {
            instance = new ConnectionPool(size);
        }
        return instance;
    }

    public Connection getConnection() throws InterruptedException {
        return conns.take();
    }

    public void releaseConnection(Connection con) {
        conns.offer(con);
    }
}
