package com.gardner.gameanalysis.labyrinth;

import java.util.Map;

import com.gardner.gameanalysis.framework.Action;
import com.gardner.gameanalysis.framework.GameState;
import com.gardner.gameanalysis.framework.SingleActionState;

public abstract class LabyrinthState extends SingleActionState {
    protected BoardState boardState;

    protected LabyrinthState(BoardState boardState) {
        this.boardState = boardState;
    }

    public abstract boolean isMoveBoardState();

    public BoardState getBoardState() {
        return boardState;
    }

    @Override
    public boolean transitionHadRandomComponent() {
        return boardState.transitionHadRandomComponent();
    }

    @Override
    public SingleActionState takeAction(Action action) {
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

    @Override
    public int getCurrentPlayer() {
        return boardState.getCurrentPlayer();
    }
}
