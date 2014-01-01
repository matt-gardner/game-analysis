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
}
