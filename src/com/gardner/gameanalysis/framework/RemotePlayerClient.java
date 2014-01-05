package com.gardner.gameanalysis.framework;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public abstract class RemotePlayerClient implements Player {

    private String host;
    private int port;
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public RemotePlayerClient(String host, int port) throws IOException {
        System.out.println("Attempting to connect to player server at " + host + ":" + port);
        socket = new Socket(host, port);
        System.out.println("Connected: " + socket.isConnected());
        in = new ObjectInputStream(socket.getInputStream());
        out = new ObjectOutputStream(socket.getOutputStream());
    }

    public void listen() {
        try {
            PlayerMessage message = null;
            while ((message = (PlayerMessage) in.readObject()) != null) {
                if (message.getActions() == null) {
                    notifyState(message.getState());
                } else {
                    Action action = pickAction(message.getState(), message.getActions());
                    out.writeObject(new PlayerResponse(action));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
