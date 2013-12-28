package com.gardner.gameanalysis.tictactoe;

import com.gardner.gameanalysis.framework.Action;

public class TicTacToeAction implements Action {
    private final int playerNum;
    private final int x;
    private final int y;

    public TicTacToeAction(int playerNum, int x, int y) {
        this.playerNum = playerNum;
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Player " + (playerNum + 1) + "; x: " + x + "; y: " + y;
    }

    public int getPlayerNum() {
        return playerNum;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
