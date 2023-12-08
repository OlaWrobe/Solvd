package com.solvd.realestate.threads;

public class Connection {
    private static int counter = 0;

    public static void work() {
        counter++;
        System.out.println("Working " + counter);
    }
}