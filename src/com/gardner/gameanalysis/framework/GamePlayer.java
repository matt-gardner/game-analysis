package com.gardner.gameanalysis.framework;

import java.io.Console;
import java.util.List;

import com.gardner.gameanalysis.tictactoe.TicTacToe;

public class GamePlayer {
    private Game game;

    public GamePlayer(Game game) {
        this.game = game;
    }

    public void play() {
        Console console = System.console();
        GameState state = game.getInitialState();
        while (!state.isGameOver()) {
            System.out.println(state);
            List<Action> actions = state.getAvailableActions();
            for (int i = 0; i < actions.size(); i++) {
                System.out.println("(" + (i + 1) + "): " + actions.get(i));
            }
            String input = console.readLine("Which action do you want to take? ");
            int action = Integer.parseInt(input);
            state = state.takeAction(actions.get(action - 1));
        }
        System.out.println("Game over, winner is player " + state.getWinner());
    }

    public static void main(String[] args) {
        GamePlayer player = new GamePlayer(new TicTacToe());
        player.play();
    }
}
