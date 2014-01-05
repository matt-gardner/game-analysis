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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((orientation == null) ? 0 : orientation.hashCode());
        result = prime * result + ((piece == null) ? 0 : piece.hashCode());
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
        PieceOnBoard other = (PieceOnBoard) obj;
        if (orientation != other.orientation)
            return false;
        if (piece == null) {
            if (other.piece != null)
                return false;
        } else if (!piece.equals(other.piece))
            return false;
        return true;
    }
}
