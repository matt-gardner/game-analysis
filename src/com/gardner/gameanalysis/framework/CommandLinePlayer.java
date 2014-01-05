package com.gardner.gameanalysis.framework;

import java.io.Console;
import java.util.List;

public class CommandLinePlayer implements Player {

    private Console console = System.console();

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
        // If there are two CommandLinePlayers on the same terminal, this could be ugly.  But oh
        // well.
        System.out.println(state);
    }
}
