package com.gardner.gameanalysis.tictactoe;

import com.gardner.gameanalysis.framework.Game;

public class TicTacToe implements Game {
    public TicTacToeState getInitialState() {
        return new TicTacToeState(0);
    }
}
