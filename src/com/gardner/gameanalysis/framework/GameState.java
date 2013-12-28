package com.gardner.gameanalysis.framework;

import java.util.List;

public interface GameState {
    public boolean transitionHadRandomComponent();

    public List<Action> getAvailableActions();

    public GameState takeAction(Action action);

    public boolean isGameOver();

    public int getWinner();

    public int getNumPlayers();

    public GameState getStateVisibleToPlayer(int playerNum);
}
