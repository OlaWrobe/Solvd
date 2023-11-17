package com.solvd.realestate.person;

class Notification implements AutoCloseable {
    public void printNotification() {

    }

    @Override
    public void close() {
        System.out.println("Closing the resource");
    }
}