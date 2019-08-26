package com.github.gergelyszaz.bgs.game;

import com.github.gergelyszaz.bgs.game.model.action.ActionManager;

public class GameContext {
    private VariableManager variableManager;
    private ActionManager actionManager;
    private InternalManager internalManager;
    private SelectableManager selectableManager;

    public GameContext(VariableManager variableManager, ActionManager actionManager, InternalManager internalManager,
            SelectableManager selectableManager) {

        this.variableManager = variableManager;
        this.actionManager = actionManager;
        this.internalManager = internalManager;
        this.selectableManager = selectableManager;
    }

    public VariableManager getVariableManager() {
        return variableManager;
    }

    public ActionManager getActionManager() {
        return actionManager;
    }

    public InternalManager getInternalManager() {
        return internalManager;
    }

    public SelectableManager getSelectableManager() {
        return selectableManager;
    }
}