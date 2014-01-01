package com.gardner.gameanalysis.labyrinth;

public class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isOpposite(Point other) {
        if (x == other.x) {
            if (y == 0 && other.y == 6) return true;
            if (y == 6 && other.y == 0) return true;
        } else if (y == other.y) {
            if (x == 0 && other.x == 6) return true;
            if (x == 6 && other.x == 0) return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return super.hashCode();
    }
}
