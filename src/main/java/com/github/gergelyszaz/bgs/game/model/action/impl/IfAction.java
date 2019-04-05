package com.github.gergelyszaz.bgs.game.model.action.impl;

import com.github.gergelyszaz.bgl.bgl.Action;
import com.github.gergelyszaz.bgs.game.model.action.ActionManager;
import com.github.gergelyszaz.bgs.game.VariableManager;

/**
 * Created by gergely.szaz on 2016. 10. 16..
 */
public class IfAction extends ConditionalAction {

    public IfAction(VariableManager variableManager, Action action,
                    ActionManager actionManager) {

        super(variableManager, action, actionManager);

    }

    @Override
    public void Execute() throws IllegalAccessException {

        super.Execute();
    }

    @Override
    public String toString() {
        return "IF " + super.toString();
    }
}
