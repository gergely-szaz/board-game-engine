package com.github.gergelyszaz.bgs.game.model.action.impl;

import com.github.gergelyszaz.bgs.game.GameContext;
import com.github.gergelyszaz.bgs.game.model.action.ConcreteAction;

public class NopAction implements ConcreteAction {

	@Override
	public void execute(GameContext context) throws IllegalAccessException {
		//Intentionally left empty
	}

	@Override
	public String toString(){
		return this.getClass().getSimpleName();
	}
}
