package com.github.gergelyszaz.bgs.game.model.action.impl;
import com.github.gergelyszaz.bgl.bgl.Action;
import com.github.gergelyszaz.bgs.game.GameContext;


/**
 * Created by gergely.szaz on 2016. 10. 16..
 */
public class IfAction extends ConditionalAction {

    public IfAction(Action action) {
        super(action);
    }

    @Override
    public void execute(GameContext context) throws IllegalAccessException {
        super.execute(context);
    }

    @Override
    public String toString() {
        return "IF " + super.toString();
    }
}
