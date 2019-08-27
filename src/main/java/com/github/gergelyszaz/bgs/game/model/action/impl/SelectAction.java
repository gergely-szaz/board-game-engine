package com.github.gergelyszaz.bgs.game.model.action.impl;

import com.github.gergelyszaz.bgs.game.model.action.AbstractAction;
import com.github.gergelyszaz.bgs.game.*;
import com.github.gergelyszaz.bgl.bgl.Action;


/**
 * Created by gergely.szaz on 2016. 10. 16..
 */
public class SelectAction extends AbstractAction {
	private String toVar;

	public SelectAction(Action action) {
		super(action);
		this.toVar = action.getToVar().getName();
	}

	@Override
	public void execute(GameContext context) throws IllegalAccessException {
		context.getSelectableManager().setSelectableObjects(o -> {
			context.getVariableManager().store(VariableManager.GLOBAL.THIS, o);
			return new ArithmeticManager(context.getVariableManager()).evaluate(action.getCondition());
		}, this.toVar);
	}
}
