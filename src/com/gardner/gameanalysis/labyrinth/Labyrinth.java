package com.gardner.gameanalysis.labyrinth;

import com.gardner.gameanalysis.framework.Game;

public class Labyrinth implements Game {
    private final int numPlayers;
    private final int numCards;

    public Labyrinth(int numPlayers, int numCards) {
        if (numPlayers < 1 || numPlayers > 4) {
            throw new IllegalArgumentException("Invalid number of players");
        }
        this.numPlayers = numPlayers;
        if (numCards < numPlayers || numCards > 24) {
            throw new IllegalArgumentException("Invalid number of cards");
        }
        this.numCards = numCards;
    }

    public LabyrinthState getInitialState() {
        return new MoveBoardState(numPlayers, numCards);
    }
}
