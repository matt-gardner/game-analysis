package com.gardner.gameanalysis.framework;

import java.io.Serializable;
import java.util.List;

public class PlayerMessage implements Serializable {
    private PlayerVisibleState state;
    private List<Action> actions;

    public PlayerMessage(PlayerVisibleState state, List<Action> actions) {
        this.state = state;
        this.actions = actions;
    }

    public PlayerVisibleState getState() {
        return state;
    }

    public List<Action> getActions() {
        return actions;
    }
}
