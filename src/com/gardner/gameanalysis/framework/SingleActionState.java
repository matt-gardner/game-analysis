package com.gardner.gameanalysis.framework;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A GameState that simplifies getAvailableActions for games that only ever have one move at a
 * time.
 */
public abstract class SingleActionState implements GameState {
    protected List<Action> availableActions;

    @Override
    public final Map<Integer, List<Action>> getAvailableActions() {
        ensureAvailableActions();
        Map<Integer, List<Action>> actions = new HashMap<Integer, List<Action>>();
        actions.put(getCurrentPlayer(), availableActions);
        return actions;
    }

    @Override
    public final GameState takeActions(Map<Integer, Action> actions) {
        if (actions.size() != 1) {
            throw new IllegalArgumentException("Only one action at a time here, please");
        }
        if (!actions.keySet().contains(getCurrentPlayer())) {
            throw new IllegalArgumentException("It's not your turn");
        }
        Action action = actions.get(getCurrentPlayer());
        ensureAvailableActions();
        if (!availableActions.contains(action)) {
            throw new IllegalArgumentException("Action not in set of available actions");
        }
        return takeAction(action);
    }

    protected void ensureAvailableActions() {
        if (availableActions == null) {
            availableActions = computeAvailableActions();
        }
    }

    protected abstract List<Action> computeAvailableActions();

    protected abstract int getCurrentPlayer();

    protected abstract SingleActionState takeAction(Action action);
}
