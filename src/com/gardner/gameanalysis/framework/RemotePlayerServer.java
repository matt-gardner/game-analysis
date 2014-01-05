package com.gardner.gameanalysis.framework;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class RemotePlayerServer implements Player {

    private ServerSocket socketServer;
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private int port;

    public RemotePlayerServer(int port) throws IOException {
        this.port = port;
        socketServer = new ServerSocket(port);
        port = socketServer.getLocalPort();
    }

    public void connect() throws IOException {
        System.out.println("Remote player server waiting for a connection on port " + port);
        socket = socketServer.accept();
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
        System.out.println("Connected");
    }

    @Override
    public Action pickAction(PlayerVisibleState state, List<Action> actions) {
        try {
            out.writeObject(new PlayerMessage(state, actions));
            out.reset();
            PlayerResponse response = (PlayerResponse) in.readObject();
            return response.getAction();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void notifyState(PlayerVisibleState state) {
        try {
            out.writeObject(new PlayerMessage(state, null));
            out.reset();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
