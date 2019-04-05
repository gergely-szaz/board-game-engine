package com.github.gergelyszaz.bgs.game.model.action.impl;

import com.github.gergelyszaz.bgs.game.model.action.AbstractAction;
import com.github.gergelyszaz.bgs.game.*;
import com.github.gergelyszaz.bgs.game.model.Token;
import com.github.gergelyszaz.bgl.bgl.Action;

/**
 * Created by gergely.szaz on 2016. 10. 16..
 */
public class DestroyAction extends AbstractAction {

	private final InternalManager internalManager;
	private BGLUtil bglUtil = new BGLUtil();

	public DestroyAction(VariableManager variableManager,
								Action action,
								InternalManager game) {

		super(variableManager, action);
		this.internalManager = game;
	}

	@Override
	public void Execute() throws IllegalAccessException {

		Object object =
				variableManager.getReference(bglUtil.toString(action.getSelected()));
		if (!(object instanceof Token)) {
			throw new IllegalAccessException(object + " is not a TOKEN");
		}

		Token token = ((Token) object);
		token.Destroy();
		internalManager.removeToken(token);

	}
}
