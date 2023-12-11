package com.solvd.realestate.threads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Connection {
    private final static Logger LOGGER = LogManager.getLogger(ConnectionTaskAsynch.class);
    private static int idCounter = 0;
    private static int counter = 0;
    private int id;

    public Connection() {
        this.id = ++idCounter;
    }

    public int getId() {
        return id;
    }

    public static void work() {
        counter++;
        LOGGER.info("Working " + counter);
    }
}