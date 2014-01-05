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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + playerNum;
        result = prime * result + ((point == null) ? 0 : point.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MovePieceAction other = (MovePieceAction) obj;
        if (playerNum != other.playerNum)
            return false;
        if (point == null) {
            if (other.point != null)
                return false;
        } else if (!point.equals(other.point))
            return false;
        return true;
    }
}
