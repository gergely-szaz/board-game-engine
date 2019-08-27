package com.github.gergelyszaz.bgs.game.model.action.impl;

import com.github.gergelyszaz.bgs.game.GameContext;
import com.github.gergelyszaz.bgs.game.model.action.ConcreteAction;

/**
 * Created by mad on 2016. 10. 24..
 */
public class GotoAction implements ConcreteAction {

	private final ConcreteAction toAction;

	public GotoAction(ConcreteAction toAction){
		this.toAction=toAction;
	}
	@Override
	public void execute(GameContext context) throws IllegalAccessException {
		context.getActionManager().setNextAction(toAction);
	}

	@Override
	public String toString(){
		return this.getClass().getSimpleName();
	}
}
