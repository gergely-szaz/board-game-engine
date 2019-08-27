package com.github.gergelyszaz.bgs.game.model.action;

import com.github.gergelyszaz.bgl.bgl.Action;

public abstract class AbstractAction implements ConcreteAction {
    protected Action action;
    protected String name;

    public AbstractAction(Action action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
