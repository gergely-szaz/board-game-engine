package com.github.gergelyszaz.bgs.game.model.action.impl;

import com.github.gergelyszaz.bgs.game.model.action.AbstractAction;
import com.github.gergelyszaz.bgl.bgl.Action;
import com.github.gergelyszaz.bgs.game.*;
import com.github.gergelyszaz.bgs.game.model.Token;


/**
 * Created by gergely.szaz on 2016. 10. 16..
 */
public class DestroyAction extends AbstractAction {
	private BGLUtil bglUtil = new BGLUtil();

	public DestroyAction(Action action) {

		super(action);
	}

	@Override
	public void execute(GameContext context) throws IllegalAccessException {

		Object object =
				context.getVariableManager().getReference(bglUtil.toString(action.getSelected()));
		if (!(object instanceof Token)) {
			throw new IllegalAccessException(object + " is not a TOKEN");
		}

		Token token = ((Token) object);
		token.Destroy();
		context.getInternalManager().removeToken(token);

	}
}
