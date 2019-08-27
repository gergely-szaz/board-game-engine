package com.github.gergelyszaz.bgs.game.model.action.impl;

import com.github.gergelyszaz.bgl.bgl.Action;
import com.github.gergelyszaz.bgs.game.model.action.*;
import com.github.gergelyszaz.bgs.game.*;

/**
 * Created by mad on 2016. 10. 24..
 */
public abstract class ConditionalAction extends AbstractAction {
    protected com.github.gergelyszaz.bgs.game.model.action.ConcreteAction skipAction;

    public ConditionalAction(Action action) {

        super(action);
    }

    public void setSkipAction(com.github.gergelyszaz.bgs.game.model.action.ConcreteAction action) {
        this.skipAction = action;
    }

    @Override
    public void execute(GameContext context) throws IllegalAccessException {
        ArithmeticManager arithmeticManager = new ArithmeticManager(context.getVariableManager());
        if (!arithmeticManager.evaluate(action.getCondition())) {
            context.getActionManager().setNextAction(skipAction);
        }

    }

    @Override
    public String toString() {
        return super.toString();
    }


}
