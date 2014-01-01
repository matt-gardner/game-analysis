package com.gardner.gameanalysis.labyrinth;

import java.util.Random;

/**
 * Each BoardPiece has a default Orientation.  That default corresponds to NORTH.  We combine a
 * BoardPiece and an Orientation to create a PieceOnBoard.  We also use Orientation to represent
 * the maze parts on each piece.
 */
public enum Orientation {

    NORTH(0),
    EAST(90),
    SOUTH(180),
    WEST(270);

    private int degrees;
    private Orientation(int degrees) {
        this.degrees = degrees;
    }

    private static Orientation getByDegrees(int degrees) {
        if (degrees == 0) {
            return NORTH;
        } else if (degrees == 90) {
            return EAST;
        } else if (degrees == 180) {
            return SOUTH;
        } else if (degrees == 270) {
            return WEST;
        } else {
            throw new IllegalArgumentException("Unknown orientation degrees: " + degrees);
        }
    }

    public Orientation rotate(Orientation rotateBy) {
        int newDegrees = (degrees + rotateBy.degrees) % 360;
        return getByDegrees(newDegrees);
    }

    public static Random random = new Random();

    public static Orientation random() {
        int num = random.nextInt(4);
        if (num == 0) return NORTH;
        else if (num == 1) return EAST;
        else if (num == 2) return SOUTH;
        else if (num == 3) return WEST;
        else throw new RuntimeException("Something weird happened");
    }
}
