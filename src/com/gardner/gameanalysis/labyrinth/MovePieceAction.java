package com.gardner.gameanalysis.labyrinth;

/**
 * Represents moving a player's piece along the maze to a new square.  Represents just one thing:
 * this (x, y) Point of the player's final position.
 */
public class MovePieceAction extends LabyrinthAction {

    private final int playerNum;
    private final Point point;

    public MovePieceAction(int playerNum, Point point) {
        this.playerNum = playerNum;
        this.point = point;
    }

    @Override
    public boolean isMoveBoard() {
        return false;
    }

    public Point getPoint() {
        return point;
    }

    @Override
    public String toString() {
        return "Move player " + playerNum + " to point " + point.toString();
    }
}
