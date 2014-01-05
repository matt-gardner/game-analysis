package com.gardner.gameanalysis.framework;

import java.util.List;

public interface Player {
    public Action pickAction(PlayerVisibleState state, List<Action> actions);

    public void notifyState(PlayerVisibleState state);
}
