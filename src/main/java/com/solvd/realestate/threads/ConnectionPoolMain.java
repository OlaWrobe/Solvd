package com.solvd.realestate.threads;


public class ConnectionPoolMain {
    public static void main(String[] args) throws InterruptedException {
        int poolSize = 5;

        ConnectionPool connectionPool = ConnectionPool.getInstance(poolSize);
        for (int i = 0; i < 7; i++) {
            connectionPool.releaseConnection(new Connection());
        }
    }
}
