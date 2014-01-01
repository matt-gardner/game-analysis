package com.gardner.gameanalysis.labyrinth;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;

import com.gardner.gameanalysis.framework.Action;
import com.gardner.gameanalysis.framework.GameState;

public class MoveBoardState extends LabyrinthState {
    public MoveBoardState(int numPlayers, int numCards) {
        super(new BoardState(numPlayers, numCards));
    }

    public MoveBoardState(BoardState initialState, MovePieceAction action) {
        super(new BoardState(initialState, action));
    }

    public MoveBoardState(BoardState boardState) {
        super(boardState);
    }

    private static List<Point> availablePoints = Arrays.asList(
            new Point(0, 1),
            new Point(0, 3),
            new Point(0, 5),
            new Point(6, 1),
            new Point(6, 3),
            new Point(6, 5),
            new Point(1, 0),
            new Point(3, 0),
            new Point(5, 0),
            new Point(1, 6),
            new Point(3, 6),
            new Point(5, 6)
            );

    @Override
    public List<Action> computeAvailableActions() {
        List<Action> actions = new ArrayList<Action>();
        Point lastMove = boardState.getLastBoardMove();
        BoardPiece freePiece = boardState.getFreePiece();
        for (Point point : availablePoints) {
            if (lastMove != null && lastMove.isOpposite(point)) {
                continue;
            }
            for (Orientation orientation : Orientation.values()) {
                actions.add(new MoveBoardAction(new PieceOnBoard(freePiece, orientation), point));
            }
        }
        return actions;
    }

    @Override
    public GameState getStateVisibleToPlayer(int playerNum) {
        // TODO(matt): Decide if this is really how I want to do this.  In particular, the purpose
        // of the GameState interface is to facilitate game analysis.  When you're actually playing
        // the game, you don't really need the functions of GameState, so why force it on the state
        // that's visible to the player?
        return null;
    }

    @Override
    public boolean isMoveBoardState() {
        return true;
    }
}
