package com.gardner.gameanalysis.labyrinth;

import java.util.List;

import com.gardner.gameanalysis.framework.Action;
import com.gardner.gameanalysis.framework.GameState;

public abstract class LabyrinthState implements GameState {
    protected BoardState boardState;
    protected List<Action> availableActions;

    protected LabyrinthState(BoardState boardState) {
        this.boardState = boardState;
    }

    public abstract boolean isMoveBoardState();

    public BoardState getBoardState() {
        return boardState;
    }

    @Override
    public final List<Action> getAvailableActions() {
        if (availableActions == null) {
            availableActions = computeAvailableActions();
        }
        return availableActions;
    }

    protected abstract List<Action> computeAvailableActions();

    @Override
    public boolean transitionHadRandomComponent() {
        return boardState.transitionHadRandomComponent();
    }

    @Override
    public GameState takeAction(Action action) {
        if (!getAvailableActions().contains(action)) {
            throw new IllegalArgumentException("Action not in set of available actions");
        }
        if (isMoveBoardState()) {
            return new MovePieceState(boardState, (MoveBoardAction) action);
        } else {
            return new MoveBoardState(boardState, (MovePieceAction) action);
        }
    }

    @Override
    public boolean isGameOver() {
        return boardState.isGameOver();
    }

    @Override
    public int getWinner() {
        return boardState.getWinner();
    }

    @Override
    public int getNumPlayers() {
        return boardState.getNumPlayers();
    }
}
