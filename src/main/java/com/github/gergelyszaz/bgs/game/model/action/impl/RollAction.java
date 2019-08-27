package com.github.gergelyszaz.bgs.game.model.action.impl;

import com.github.gergelyszaz.bgs.game.model.action.AbstractAction;
import com.github.gergelyszaz.bgl.bgl.Action;
import com.github.gergelyszaz.bgs.game.*;

import java.util.*;

/**
 * Created by gergely.szaz on 2016. 10. 16..
 */
public class RollAction extends AbstractAction {
    private BGLUtil bglUtil = new BGLUtil();

    public RollAction(Action action) {
        super(action);
    }

    @Override
    public void execute(GameContext context) throws IllegalAccessException {
        Random r = new Random();

        List<Integer> results = new ArrayList<>();
        for (int i = 0; i < action.getNumberOfDice(); i++) {
            int rollresult = r.nextInt(action.getTo() - action.getFrom()) + action.getFrom();
            results.add(rollresult);
        }

        int result = 0;
        for (int i : results) {
            result += i;
        }

        String variablePath = bglUtil.toString(action.getToVar());
        context.getVariableManager().store(variablePath, result);
    }
}
