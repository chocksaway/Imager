package com.chocksaway.imager.peer;

import org.junit.jupiter.api.Test;

public class TestSimpleSocket {
    @Test
    public void testSimpleSocket() {
        new Thread(() -> {
            SimpleSocket simpleSocket = new SimpleSocket(9876);

        }).start();


        // Add a delay to ensure the server starts before the client
        try {
            Thread.sleep(2000); // 2 seconds delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            SimpleSocketClient simpleSocketClient = new SimpleSocketClient(9876);
        }).start();

        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
