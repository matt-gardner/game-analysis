package com.gardner.gameanalysis.labyrinth;

import com.gardner.gameanalysis.framework.Action;

/**
 * A LabyrinthAction is either changing the maze by pushing a piece into the board (taken in the
 * MoveBoardState), or moving a player along the maze (taken in the MovePieceState).
 */
public abstract class LabyrinthAction implements Action {
    public abstract boolean isMoveBoard();
}
