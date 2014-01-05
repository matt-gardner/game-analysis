package com.gardner.gameanalysis.framework;

import java.io.Console;
import java.io.IOException;
import java.util.List;

public class RemoteCommandLinePlayer extends RemotePlayerClient {
    private Console console = System.console();

    public RemoteCommandLinePlayer(String host, int port) throws IOException {
        super(host, port);
    }

    @Override
    public Action pickAction(PlayerVisibleState state, List<Action> actions) {
        System.out.println(state);
        for (int i = 0; i < actions.size(); i++) {
            System.out.println("(" + (i + 1) + "): " + actions.get(i));
        }
        String input = console.readLine("Which action do you want to take? ");
        int action = Integer.parseInt(input);
        return actions.get(action - 1);
    }

    @Override
    public void notifyState(PlayerVisibleState state) {
        System.out.println(state);
    }

    public static void main(String[] args) throws IOException {
        String host = args[0];
        int port = Integer.parseInt(args[1]);
        RemotePlayerClient player = new RemoteCommandLinePlayer(host, port);
        player.listen();
    }
}
