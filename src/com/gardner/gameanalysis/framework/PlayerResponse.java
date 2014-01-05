package com.gardner.gameanalysis.framework;

import java.io.Serializable;

public class PlayerResponse implements Serializable {
    private Action action;

    public PlayerResponse(Action action) {
        this.action = action;
    }

    public Action getAction() {
        return action;
    }
}
