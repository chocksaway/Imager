package com.chocksaway.imager.peer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;

public class SimpleSocket {
    private ServerSocket server;
    private final int port;

    public SimpleSocket(final int port) {
        this.port = port;
        this.configure();
        this.listen();
    }

    private static final Logger logger = LoggerFactory.getLogger(SimpleSocket.class);

    private void configure() {
        try {
            this.server = new ServerSocket(this.port);
        } catch (IOException e) {
            logger.debug("Error creating server socket: {}", e.getMessage());

        }
    }

    private void listen() {
        while(true) {
            logger.debug("Waiting for the client request");
            try {
                var socket = server.accept();

                // read
                var ois = new ObjectInputStream(socket.getInputStream());
                var message = (String) ois.readObject();
                logger.debug("Message Received: {}", message);

                if (message.equals("exit")) {
                    logger.debug("Shutting down Socket server!!");
                    server.close();
                    break;
                }

                var oos = new ObjectOutputStream(socket.getOutputStream());

                // write
                oos.writeObject("ack" + message);
                ois.close();
                oos.close();
                socket.close();
            } catch (IOException | ClassNotFoundException exception) {
                logger.debug("Error accepting client request: {}", exception.getMessage());
            }
        }
    }
}