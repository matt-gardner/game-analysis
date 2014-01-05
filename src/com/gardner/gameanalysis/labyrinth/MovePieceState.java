package com.gardner.gameanalysis.labyrinth;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.gardner.gameanalysis.framework.Action;
import com.gardner.gameanalysis.framework.PlayerVisibleState;

public class MovePieceState extends LabyrinthState {
    public MovePieceState(int numPlayers, int numCards) {
        super(new BoardState(numPlayers, numCards));
    }

    public MovePieceState(BoardState initialState, MoveBoardAction action) {
        super(new BoardState(initialState, action));
    }

    @Override
    public List<Action> computeAvailableActions() {
        List<Action> actions = new ArrayList<Action>();
        Set<Point> reachableSpaces = boardState.getReachableSpacesForCurrentPlayer();
        for (Point point : reachableSpaces) {
            actions.add(new MovePieceAction(boardState.getCurrentPlayer(), point));
        }
        return actions;
    }

    @Override
    public PlayerVisibleState getStateVisibleToPlayer(int playerNum) {
        // TODO(matt): Decide if this is really how I want to do this.  In particular, the purpose
        // of the GameState interface is to facilitate game analysis.  When you're actually playing
        // the game, you don't really need the functions of GameState, so why force it on the state
        // that's visible to the player?
        return null;
    }

    @Override
    public boolean isMoveBoardState() {
        return false;
    }
}

