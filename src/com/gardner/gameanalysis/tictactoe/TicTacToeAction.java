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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TicTacToeAction other = (TicTacToeAction) obj;
        if (playerNum != other.playerNum)
            return false;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }
}
