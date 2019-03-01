package com.github.gergelyszaz.bgs.action.impl;

import com.github.gergelyszaz.bgl.bgl.Action;
import com.github.gergelyszaz.bgs.action.*;
import com.github.gergelyszaz.bgs.game.*;

/**
 * Created by mad on 2016. 10. 24..
 */
public abstract class ConditionalAction extends AbstractAction {
    protected com.github.gergelyszaz.bgs.action.Action skipAction;
    protected ActionManager actionManager;
    protected ArithmeticManager arithmeticManager ;

    public ConditionalAction(VariableManager variableManager,
                             Action action, ActionManager actionManager) {

        super(variableManager, action);
        this.actionManager = actionManager;
        this.arithmeticManager = new ArithmeticManager(variableManager);
    }

    public void setSkipAction(com.github.gergelyszaz.bgs.action.Action action) {
        this.skipAction = action;
    }

    @Override
    public void Execute() throws IllegalAccessException {
        if (!arithmeticManager.evaluate(action.getCondition())) {
            actionManager.setNextAction(skipAction);
        }

    }

    @Override
    public String toString() {
        return super.toString();
    }


}
