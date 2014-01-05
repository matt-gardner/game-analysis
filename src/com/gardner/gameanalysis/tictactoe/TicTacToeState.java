package com.gardner.gameanalysis.tictactoe;

import java.util.ArrayList;
import java.util.List;

import com.gardner.gameanalysis.framework.Action;
import com.gardner.gameanalysis.framework.GameState;
import com.gardner.gameanalysis.framework.PlayerVisibleState;
import com.gardner.gameanalysis.framework.SingleActionState;

public class TicTacToeState extends SingleActionState implements PlayerVisibleState {

    private int[][] board;
    private boolean gameOver;
    private int winner;
    private final int currentPlayer;
    private List<Action> availableActions;

    public TicTacToeState(int currentPlayer) {
        this.currentPlayer = currentPlayer;
        board = new int[3][3];
        gameOver = false;
    }

    @Override
    public boolean transitionHadRandomComponent() {
        return false;
    }

    @Override
    public int getCurrentPlayer() {
        return currentPlayer;
    }

    @Override
    public List<Action> computeAvailableActions() {
        availableActions = new ArrayList<Action>();
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (board[x][y] == 0) {
                    availableActions.add(new TicTacToeAction(currentPlayer, x, y));
                }
            }
        }
        return availableActions;
    }

    @Override
    public SingleActionState takeAction(Action a) {
        TicTacToeAction action = (TicTacToeAction) a;
        int nextPlayer = (currentPlayer + 1) % 2;
        TicTacToeState nextState = new TicTacToeState(nextPlayer);
        nextState.board = board.clone();
        nextState.board[action.getX()][action.getY()] = action.getPlayerNum() + 1;
        int winner = checkForWin(nextState.board);
        if (winner != -1) {
            nextState.gameOver = true;
            nextState.winner = winner;
        }
        return nextState;
    }

    @Override
    public boolean isGameOver() {
        return gameOver;
    }

    @Override
    public int getWinner() {
        return winner;
    }

    @Override
    public int getNumPlayers() {
        return 2;
    }

    @Override
    public PlayerVisibleState getStateVisibleToPlayer(int playerNum) {
        return this;
    }

    public int[][] getBoard() {
        return board.clone();
    }

    @Override
    public String toString() {
        String result = "\n";
        result += "      y\n\n";
        result += "  " + board[0][0] + " | " + board[0][1] + " | " + board[0][2] + "\n";
        result += "  ---------\n";
        result += "x " + board[1][0] + " | " + board[1][1] + " | " + board[1][2] + "\n";
        result += "  ---------\n";
        result += "  " + board[2][0] + " | " + board[2][1] + " | " + board[2][2] + "\n";
        result += "\nPlayer " + (currentPlayer + 1) + "'s turn\n";
        result += "\n";
        return result;
    }

    private int checkForWin(int[][] board) {
        if (board[0][0] == 1 && board[0][1] == 1 && board[0][2] == 1) return 1;
        if (board[1][0] == 1 && board[1][1] == 1 && board[1][2] == 1) return 1;
        if (board[2][0] == 1 && board[2][1] == 1 && board[2][2] == 1) return 1;
        if (board[0][0] == 1 && board[1][0] == 1 && board[2][0] == 1) return 1;
        if (board[0][1] == 1 && board[1][1] == 1 && board[2][1] == 1) return 1;
        if (board[0][2] == 1 && board[1][2] == 1 && board[2][2] == 1) return 1;
        if (board[0][0] == 1 && board[1][1] == 1 && board[2][2] == 1) return 1;
        if (board[2][2] == 1 && board[1][1] == 1 && board[0][0] == 1) return 1;

        if (board[0][0] == 2 && board[0][1] == 2 && board[0][2] == 2) return 2;
        if (board[1][0] == 2 && board[1][1] == 2 && board[1][2] == 2) return 2;
        if (board[2][0] == 2 && board[2][1] == 2 && board[2][2] == 2) return 2;
        if (board[0][0] == 2 && board[1][0] == 2 && board[2][0] == 2) return 2;
        if (board[0][1] == 2 && board[1][1] == 2 && board[2][1] == 2) return 2;
        if (board[0][2] == 2 && board[1][2] == 2 && board[2][2] == 2) return 2;
        if (board[0][0] == 2 && board[1][1] == 2 && board[2][2] == 2) return 2;
        if (board[2][2] == 2 && board[1][1] == 2 && board[0][0] == 2) return 2;

        return -1;
    }
}
