package com.github.gergelyszaz.bgs.action.impl;

import com.github.gergelyszaz.bgs.action.AbstractAction;
import com.github.gergelyszaz.bgs.game.*;
import com.github.gergelyszaz.bgl.bgl.Action;

/**
 * Created by gergely.szaz on 2016. 10. 16..
 */
public class SelectAction extends AbstractAction {

	private final SelectableManager selectableManager;
	private String toVar;
	private ArithmeticManager arithmeticManager;

	public SelectAction(VariableManager variableManager,
							  Action action,
							  SelectableManager selectableManager) {

		super(variableManager, action);
		this.selectableManager = selectableManager;
		this.toVar = action.getToVar().getName();

		this.arithmeticManager = new ArithmeticManager(variableManager);
	}

	@Override
	public void Execute() throws IllegalAccessException {
		selectableManager.setSelectableObjects(o -> {
			variableManager.store(VariableManager.GLOBAL.THIS, o);
			return arithmeticManager.evaluate(action.getCondition());
		}, this.toVar);
	}

	@Override
	public String toString() {

		return super.toString() + " " +
				selectableManager.getSelectableObjects().size();
	}
}
