package com.gardner.gameanalysis.framework;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gardner.gameanalysis.tictactoe.TicTacToe;

public class GameDriver {
    private Game game;
    private List<Player> players;

    public GameDriver(Game game, Player ... players) {
        this.game = game;
        this.players = Arrays.asList(players);
    }

    public void play() {
        GameState state = game.getInitialState();
        for (int i = 0; i < players.size(); i++) {
            players.get(i).notifyState(state.getStateVisibleToPlayer(i));
        }
        while (!state.isGameOver()) {
            Map<Integer, List<Action>> actions = state.getAvailableActions();
            Map<Integer, Action> actionsToTake = new HashMap<Integer, Action>();
            for (int player : actions.keySet()) {
                Action a = players.get(player).pickAction(state.getStateVisibleToPlayer(player),
                                                          actions.get(player));
                actionsToTake.put(player, a);
            }
            state = state.takeActions(actionsToTake);
            for (int i = 0; i < players.size(); i++) {
                players.get(i).notifyState(state.getStateVisibleToPlayer(i));
            }
        }
        System.out.println("Game over, winner is player " + state.getWinner());
    }

    public static void main(String[] args) throws IOException {
        Player[] players = {new RemotePlayerServer(8888), new RemotePlayerServer(8889)};
        System.out.println("Connecting players");
        for (Player player : players) {
            if (player instanceof RemotePlayerServer) {
                ((RemotePlayerServer) player).connect();
            }
        }
        System.out.println("Players connected");
        System.out.println("Starting game");
        GameDriver driver = new GameDriver(new TicTacToe(), players);
        driver.play();
    }
}
