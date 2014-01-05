package com.gardner.gameanalysis.framework;

import java.util.List;
import java.util.Map;

/**
 * All of the state that a particular game has should be found in this class.  This class is the
 * main point of interaction with the game framework - while there is a Game interface, that
 * interface's primary job is to _start_ a game.  When _playing_ a game, we just use GameState.
 * This state is manipulable (we tell the state to take actions), and it contains all information
 * known to any player.  In order to send a limited amount of information to a particular player in
 * a read-only fashion, we have the PlayerVisibleState interface.
 */
public interface GameState {
    /**
     * Whether or not there was some stochasticity in the creation of this state from the previous
     * state.  This is purely for the analysis framework and has no bearing in the playing of the
     * game.  Note that if the only stochasticity is found in determining the order of the players,
     * this method should return false.
     */
    public boolean transitionHadRandomComponent();

    /**
     * Returns a map from player number (0-indexed) to a list of actions available to the player
     * in this state.  Most games will probably only have one entry in the map at a time, but to
     * allow for games where players make simultaneous decisions, we use a map.
     */
    public Map<Integer, List<Action>> getAvailableActions();

    /**
     * Returns a new state that is determined by the collection of actions taken by each player.
     * The input parameter is a map from player number to the action taken by that player.  Most
     * games will probably only have one item in that map at a time, but to allow for games that
     * have simultaneous actions, we use a map.
     */
    public GameState takeActions(Map<Integer, Action> actions);

    public boolean isGameOver();

    public int getWinner();

    public int getNumPlayers();

    public PlayerVisibleState getStateVisibleToPlayer(int playerNum);
}
