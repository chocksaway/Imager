package com.chocksaway.imager.peer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class SimpleSocketClient {
    private final Socket socket;

    private static final Logger logger = LoggerFactory.getLogger(SimpleSocketClient.class);

    public SimpleSocketClient(int port) {
        try {
            this.socket = new Socket("localhost", port);
            this.wrapper();
        } catch (UnknownHostException e) {
            throw new RuntimeException("UnknownHostException: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException("IOException: " + e.getMessage());
        }
    }

    private void wrapper() {
        try {
            ObjectOutputStream oos;
            ObjectInputStream ois;

            while (true) {
                oos = new ObjectOutputStream(socket.getOutputStream());
                logger.debug("Sending request to Socket Server");

                // write
                oos.writeObject("0");
                ois = new ObjectInputStream(socket.getInputStream());

                String message = (String) ois.readObject();
                logger.debug("Message: {}", message);

                ois.close();
                oos.close();

                Thread.sleep(100);
            }

        } catch (UnknownHostException e) {
            logger.debug("UnknownHostException: {}", e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            logger.debug("IOException: {}", e.getMessage());
        } catch (InterruptedException e) {
            logger.debug("InterruptedException: {}", e.getMessage());
        }
    }
}
