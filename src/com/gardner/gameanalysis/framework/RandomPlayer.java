package com.gardner.gameanalysis.framework;

import java.util.List;
import java.util.Random;

public class RandomPlayer implements Player {

    private Random random = new Random();

    @Override
    public void notifyState(PlayerVisibleState state) { }

    @Override
    public Action pickAction(PlayerVisibleState state, List<Action> actions) {
        return actions.get(random.nextInt(actions.size()));
    }

}
