package com.github.gergelyszaz.bgs.game.model.action.impl;

import com.github.gergelyszaz.bgs.game.model.action.AbstractAction;
import com.github.gergelyszaz.bgs.game.*;
import com.github.gergelyszaz.bgs.game.model.Deck;
import com.github.gergelyszaz.bgl.bgl.Action;

/**
 * Created by gergely.szaz on 2016. 10. 16..
 */
public class ShuffleAction extends AbstractAction {
	BGLUtil bglUtil = new BGLUtil();

	public ShuffleAction(Action action) {

		super(action);
	}

	@Override
	public void execute(GameContext context) throws IllegalAccessException {

		Object object =
				context.getVariableManager().getReference(bglUtil.toString(action.getSelected()));
		if (!(object instanceof Deck)) {
			throw new IllegalAccessException(object + " is not a DECK");
		}
		((Deck) object).Shuffle();

	}
}
