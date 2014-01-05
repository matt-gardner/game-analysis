package com.gardner.gameanalysis.labyrinth;

/**
 * Represents pushing a board piece in a particular orientation into a one of the rows or columns
 * of the board.  We represent the row or column being pushed into by its (x, y) position.
 */
public class MoveBoardAction extends LabyrinthAction {
    private final PieceOnBoard piece;
    private final Point point;

    public MoveBoardAction(PieceOnBoard piece, Point point) {
        this.piece = piece;
        this.point = point;
    }

    @Override
    public boolean isMoveBoard() {
        return true;
    }

    public PieceOnBoard getPiece() {
        return piece;
    }

    public Point getPoint() {
        return point;
    }

    @Override
    public String toString() {
        return "Place piece\n" + piece.toString() + "at " + point.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((piece == null) ? 0 : piece.hashCode());
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
        MoveBoardAction other = (MoveBoardAction) obj;
        if (piece == null) {
            if (other.piece != null)
                return false;
        } else if (!piece.equals(other.piece))
            return false;
        if (point == null) {
            if (other.point != null)
                return false;
        } else if (!point.equals(other.point))
            return false;
        return true;
    }
}
