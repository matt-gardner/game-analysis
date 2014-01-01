package com.gardner.gameanalysis.labyrinth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Represents a Labyrinth board, containing all information that is on the board, including
 * individual players' cards.  This class is read-only, with only final members.
 */
public class BoardState {

    private static Point[] initialPositions = {
        new Point(0, 0),
        new Point(0, 6),
        new Point(6, 0),
        new Point(6, 6)
    };

    private final int numPlayers;
    private final int currentPlayer;
    private final List<List<Card>> playerCards;
    private final Map<Integer, Point> playerPositions;
    private final Board board;
    private final boolean isMoveBoard;
    private final boolean transitionWasRandom;
    private final boolean gameOver;
    private final int winner;

    /**
     * Initializes a random board state with a specified number of players and total cards (which
     * are then divided evenly among the players).
     */
    public BoardState(int numPlayers, int cardsToUse) {
        this.numPlayers = numPlayers;
        currentPlayer = 0;
        playerCards = initializePlayerCards(numPlayers, cardsToUse);
        playerPositions = initializePlayerPositions(numPlayers);
        board = initializeBoard();
        isMoveBoard = true;
        transitionWasRandom = true;
        gameOver = false;
        winner = -1;
    }

    /**
     * Creates the board state formed by starting at an initial state and taking some action.
     */
    public BoardState(BoardState initial, LabyrinthAction action) {
        if (action.isMoveBoard() != initial.isMoveBoard) {
            throw new IllegalArgumentException("Action doesn't correspond to board state");
        }
        transitionWasRandom = false;
        numPlayers = initial.numPlayers;
        isMoveBoard = !initial.isMoveBoard;
        board = initial.board;
        playerPositions = initial.playerPositions;
        playerCards = initial.playerCards;
        if (initial.isMoveBoard) {
            MoveBoardAction a = (MoveBoardAction) action;
            board.insertPiece(a.getPiece(), a.getPoint());
            movePlayersInPath(a.getPoint());
            currentPlayer = initial.currentPlayer;
        } else {
            MovePieceAction a = (MovePieceAction) action;
            playerPositions.put(initial.currentPlayer, a.getPoint());
            if (board.getItemAtPoint(a.getPoint()) == getNextPlayerItem(initial.currentPlayer)) {
                List<Card> cards = playerCards.get(initial.currentPlayer);
                cards.remove(0);
            }
            currentPlayer = (initial.currentPlayer + 1) % numPlayers;
        }
        winner = checkWinner();
        gameOver = winner != -1;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int getWinner() {
        return winner;
    }

    public boolean transitionHadRandomComponent() {
        return transitionWasRandom;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public Set<Point> getReachableSpacesForCurrentPlayer() {
        return board.getReachableSpaces(playerPositions.get(currentPlayer));
    }

    private static List<List<Card>> initializePlayerCards(int numPlayers, int cardsToUse) {
        List<List<Card>> cardLists = new ArrayList<List<Card>>();
        for (int i = 0; i < numPlayers; i++) {
            cardLists.add(new ArrayList<Card>());
        }
        List<Card> allCards = new ArrayList<Card>();
        for (Item item : Item.values()) {
            allCards.add(new Card(item));
        }
        Collections.shuffle(allCards);
        int current = 0;
        cardsToUse = (cardsToUse / numPlayers) * numPlayers;  // make sure the cards are even
        for (int i = 0; i < cardsToUse; i++) {
            cardLists.get(current).add(allCards.get(i));
            current = (current + 1) % numPlayers;
        }
        return cardLists;
    }

    private static Board initializeBoard() {
        return new Board();
    }

    public Item getNextPlayerItem(int playerNum) {
        List<Card> cards = playerCards.get(playerNum);
        if (cards.size() == 0) {
            return null;
        }
        return cards.get(0).getItem();
    }

    public Point getLastBoardMove() {
        return board.getLastMove();
    }

    public BoardPiece getFreePiece() {
        return board.getFreePiece();
    }

    private static Map<Integer, Point> initializePlayerPositions(int numPlayers) {
        Map<Integer, Point> positions = new HashMap<Integer, Point>();
        for (int i = 0; i < numPlayers; i++) {
            positions.put(i, initialPositions[i]);
        }
        return positions;
    }

    /**
     * A piece was inserted at a particular (x, y) point.  Move any players that were in the row or
     * column that was pushed.
     */
    private void movePlayersInPath(Point point) {
        if (point.getX() == 0 || point.getX() == 6) {
            int diff = 0;
            if (point.getX() == 0) {
                diff = 1;
            } else {
                diff = -1;
            }
            for (int i = 0; i < numPlayers; i++) {
                Point playerPos = playerPositions.get(i);
                if (playerPos.getY() == point.getY()) {
                    int newX = (playerPos.getX() + diff) % 7;
                    playerPositions.put(i, new Point(newX, playerPos.getY()));
                }
            }
        } else if (point.getY() == 0 || point.getY() == 6) {
            int diff = 0;
            if (point.getY() == 0) {
                diff = 1;
            } else {
                diff = -1;
            }
            for (int i = 0; i < numPlayers; i++) {
                Point playerPos = playerPositions.get(i);
                if (playerPos.getX() == point.getX()) {
                    int newY = (playerPos.getY() + diff) % 7;
                    playerPositions.put(i, new Point(playerPos.getX(), newY));
                }
            }
        }
    }

    /**
     * We have a simple test for winner - if a player has no cards left in their list, and their
     * position is their home position, we declare them to be the winner.
     */
    private int checkWinner() {
        for (int player = 0; player < numPlayers; player++) {
            if (playerCards.get(player).size() == 0
                && playerPositions.get(player) == initialPositions[player]) {
                return player;
            }
        }
        return -1;
    }
}
