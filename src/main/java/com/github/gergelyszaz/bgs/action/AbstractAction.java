package com.github.gergelyszaz.bgs.action;

import com.github.gergelyszaz.bgs.game.VariableManager;

/**
 * Created by mad on 2016. 10. 12..
 */
public abstract class AbstractAction implements com.github.gergelyszaz.bgs.action.Action {

    protected final VariableManager variableManager;
    protected com.github.gergelyszaz.bgl.bgl.Action action;
    protected String name;

    public AbstractAction(VariableManager variableManager, com.github.gergelyszaz.bgl.bgl.Action action) {
        this.variableManager = variableManager;
        this.action = action;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
