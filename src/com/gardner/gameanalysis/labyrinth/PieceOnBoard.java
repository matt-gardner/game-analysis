package com.gardner.gameanalysis.labyrinth;

import java.util.HashSet;
import java.util.Set;

/**
 * A BoardPiece that is on the board, which means that it has a specified orientation.
 */
public class PieceOnBoard {
    private final BoardPiece piece;
    private final Orientation orientation;

    public PieceOnBoard(BoardPiece piece, Orientation orientation) {
        this.piece = piece;
        this.orientation = orientation;
    }

    public BoardPiece getPiece() {
        return piece;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public Set<Orientation> getRotatedExits() {
        Set<Orientation> rotatedExits = new HashSet<Orientation>();
        for (Orientation exit : piece.getExits()) {
            rotatedExits.add(exit.rotate(orientation));
        }
        return rotatedExits;
    }

    @Override
    public String toString() {
        Set<Orientation> exits = getRotatedExits();
        String result = "";
        if (exits.contains(Orientation.NORTH)) {
            result += " N \n";
        } else {
            result += "   \n";
        }
        if (exits.contains(Orientation.WEST)) {
            result += "W";
        } else {
            result += " ";
        }
        if (piece.getItem() != null) {
            result += "*";
        } else {
            result += " ";
        }
        if (exits.contains(Orientation.EAST)) {
            result += "E\n";
        } else {
            result += " \n";
        }
        if (exits.contains(Orientation.SOUTH)) {
            result += " S \n";
        } else {
            result += "   \n";
        }
        if (piece.getItem() != null) {
            result += piece.getItem() + "\n";
        }
        return result;
    }
}
