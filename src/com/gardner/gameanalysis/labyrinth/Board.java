package com.gardner.gameanalysis.labyrinth;

import static com.gardner.gameanalysis.labyrinth.Orientation.EAST;
import static com.gardner.gameanalysis.labyrinth.Orientation.NORTH;
import static com.gardner.gameanalysis.labyrinth.Orientation.SOUTH;
import static com.gardner.gameanalysis.labyrinth.Orientation.WEST;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * A collection of PieceOnBoards and some methods for manipulating them.
 */
public class Board {
    private PieceOnBoard[][] pieces;
    private BoardPiece freePiece;
    private Point lastMoved;

    public Board() {
        pieces = new PieceOnBoard[7][7];
        initializeFixedPieces();
        List<PieceOnBoard> freePieces = initializeFreePieces();
        placeFreePieces(freePieces);
    }

    public void insertPiece(PieceOnBoard piece, Point point) {
        if (!isInsertable(point)) {
            throw new IllegalArgumentException("Tried to insert piece on fixed row or column");
        }
        if (piece.getPiece() != freePiece) {
            throw new IllegalArgumentException("Wrong piece was inserted");
        }
        if (point.getX() == 0) {
            // Here we are pushing a piece down into the board.
            for (int i = 0; i < 7; i++) {
                PieceOnBoard moved = pieces[i][point.getY()];
                pieces[i][point.getY()] = piece;
                piece = moved;
            }
            freePiece = piece.getPiece();
        } else if (point.getY() == 0) {
            // Here we are pushing a piece right into the board.
            for (int i = 0; i < 7; i++) {
                PieceOnBoard moved = pieces[point.getX()][i];
                pieces[point.getX()][i] = piece;
                piece = moved;
            }
            freePiece = piece.getPiece();
        } else if (point.getX() == 6) {
            // Here we are pushing a piece up into the board.
            for (int i = 6; i >= 0; i--) {
                PieceOnBoard moved = pieces[i][point.getY()];
                pieces[i][point.getY()] = piece;
                piece = moved;
            }
            freePiece = piece.getPiece();
        } else if (point.getY() == 6) {
            // Here we are pushing a piece up into the board.
            for (int i = 6; i >= 0; i--) {
                PieceOnBoard moved = pieces[point.getX()][i];
                pieces[point.getX()][i] = piece;
                piece = moved;
            }
            freePiece = piece.getPiece();
        }
        lastMoved = point;
    }

    public Item getItemAtPoint(Point point) {
        PieceOnBoard piece = pieces[point.getX()][point.getY()];
        return piece.getPiece().getItem();
    }

    private void initializeFixedPieces() {
        pieces[0][0] = new PieceOnBoard(new BoardPiece(null, EAST, SOUTH), NORTH);
        pieces[0][2] = new PieceOnBoard(new BoardPiece(Item.BOOK, WEST, EAST, SOUTH), NORTH);
        pieces[0][4] = new PieceOnBoard(new BoardPiece(Item.BAG_OF_GOLD, EAST, WEST, SOUTH), NORTH);
        pieces[0][6] = new PieceOnBoard(new BoardPiece(null, WEST, SOUTH), NORTH);
        pieces[2][0] = new PieceOnBoard(new BoardPiece(Item.MAP, NORTH, EAST, SOUTH), NORTH);
        pieces[2][2] = new PieceOnBoard(new BoardPiece(Item.CROWN, NORTH, EAST, SOUTH), NORTH);
        pieces[2][4] = new PieceOnBoard(new BoardPiece(Item.KEYS, WEST, EAST, SOUTH), NORTH);
        pieces[2][6] = new PieceOnBoard(new BoardPiece(Item.SKULL, WEST, NORTH, SOUTH), NORTH);
        pieces[4][0] = new PieceOnBoard(new BoardPiece(Item.RING, NORTH, EAST, SOUTH), NORTH);
        pieces[4][2] = new PieceOnBoard(new BoardPiece(Item.TREASURE_CHEST,NORTH,EAST,WEST),NORTH);
        pieces[4][4] = new PieceOnBoard(new BoardPiece(Item.EMERALD, NORTH, WEST, SOUTH), NORTH);
        pieces[4][6] = new PieceOnBoard(new BoardPiece(Item.SWORD, NORTH, WEST, SOUTH), NORTH);
        pieces[6][0] = new PieceOnBoard(new BoardPiece(null, EAST, NORTH), NORTH);
        pieces[6][2] = new PieceOnBoard(new BoardPiece(Item.CANDELABRA, WEST, EAST, NORTH), NORTH);
        pieces[6][4] = new PieceOnBoard(new BoardPiece(Item.HELMET, WEST, EAST, NORTH), NORTH);
        pieces[6][6] = new PieceOnBoard(new BoardPiece(null, WEST, NORTH), NORTH);
    }

    /**
     * Creates BoardPiece objects for every freely movable piece, and randomly picks an
     * orientation for it.
     */
    private static List<PieceOnBoard> initializeFreePieces() {
        List<PieceOnBoard> freePieces = new ArrayList<PieceOnBoard>();
        // The empty corner pieces.
        for (int i = 0; i < 10; i++) {
            freePieces.add(new PieceOnBoard(new BoardPiece(null, NORTH, EAST),
                                            Orientation.random()));
        }
        // The empty straight pieces.
        for (int i = 0; i < 12; i++) {
            freePieces.add(new PieceOnBoard(new BoardPiece(null, NORTH, SOUTH),
                                            Orientation.random()));
        }
        List<Item> teeItems = Arrays.asList(
                Item.BAT,
                Item.DRAGON,
                Item.GENIE,
                Item.GHOST,
                Item.SORCERESS,
                Item.TROLL);
        for (Item item : teeItems) {
            freePieces.add(new PieceOnBoard(new BoardPiece(item, WEST, NORTH, EAST),
                                            Orientation.random()));
        }
        List<Item> cornerItems = Arrays.asList(
                Item.MOTH,
                Item.BEETLE,
                Item.MOUSE,
                Item.LIZARD,
                Item.OWL,
                Item.SPIDER);
        for (Item item : cornerItems) {
            freePieces.add(new PieceOnBoard(new BoardPiece(item, NORTH, EAST),
                                            Orientation.random()));
        }
        assert(freePieces.size() == 34);
        return freePieces;
    }

    private void placeFreePieces(List<PieceOnBoard> freePieces) {
        List<Point> openSpaces = Arrays.asList(
            new Point(0, 1), new Point(0, 3), new Point(0, 5),
            new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(1, 3), new Point(1, 4),
            new Point(1, 5), new Point(1, 6),
            new Point(2, 1), new Point(2, 3), new Point(2, 5),
            new Point(3, 0), new Point(3, 1), new Point(3, 2), new Point(3, 3), new Point(3, 4),
            new Point(3, 5), new Point(3, 6),
            new Point(4, 1), new Point(4, 3), new Point(4, 5),
            new Point(5, 0), new Point(5, 1), new Point(5, 2), new Point(5, 3), new Point(5, 4),
            new Point(5, 5), new Point(5, 6),
            new Point(6, 1), new Point(6, 3), new Point(6, 5)
        );
        Collections.shuffle(freePieces);
        for (int i = 0; i < openSpaces.size(); i++) {
            Point p = openSpaces.get(i);
            pieces[p.getX()][p.getY()] = freePieces.get(0);
            freePieces.remove(0);
        }
        assert(freePieces.size() == 1);
        freePiece = freePieces.get(0).getPiece();
    }

    public boolean isInsertable(Point point) {
        // First check that we aren't undoing the last move.
        if (lastMoved != null && lastMoved.isOpposite(point)) return false;
        // Then check for inserting into a fixed row or column.
        if (point.getX() == 0 || point.getX() == 6) {
            if (point.getY() == 1 || point.getY() == 3 || point.getY() == 5) {
                return true;
            }
        } else if (point.getY() == 0 || point.getY() == 6) {
            if (point.getX() == 1 || point.getX() == 3 || point.getX() == 5) {
                return true;
            }
        }
        return false;
    }

    /**
     * Finds all spaces on the board that are reachable from a given point.
     */
    public Set<Point> getReachableSpaces(Point point) {
        System.out.println("Getting reachable spaces");
        Set<Point> points = new HashSet<Point>();
        LinkedList<Point> queue = new LinkedList<Point>();
        queue.add(point);
        while (queue.size() > 0) {
            Point currentPoint = queue.poll();
            int x = currentPoint.getX();
            int y = currentPoint.getY();
            PieceOnBoard piece = pieces[x][y];
            Set<Orientation> exits = piece.getRotatedExits();
            for (Orientation exit : exits) {
                int newX = -1, newY = -1;
                if (exit == NORTH) {
                    newX = x - 1;
                    newY = y;
                } else if (exit == SOUTH) {
                    newX = x + 1;
                    newY = y;
                } else if (exit == EAST) {
                    newX = x;
                    newY = y + 1;
                } else if (exit == WEST) {
                    newX = x;
                    newY = y - 1;
                }
                if (newX < 0 || newY < 0 || newX > 6 || newY > 6) {
                    // This goes off the board.
                    continue;
                }
                Point newPoint = new Point(newX, newY);
                if (!points.contains(newPoint)) {
                    queue.add(newPoint);
                }
                points.add(point);
            }
        }
        return points;
    }

    public Point getLastMove() {
        return lastMoved;
    }

    public BoardPiece getFreePiece() {
        return freePiece;
    }
}
