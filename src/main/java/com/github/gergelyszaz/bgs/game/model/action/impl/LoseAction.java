package com.github.gergelyszaz.bgs.game.model.action.impl;

import com.github.gergelyszaz.bgs.game.model.action.AbstractAction;
import com.github.gergelyszaz.bgs.game.*;
import com.github.gergelyszaz.bgs.game.model.Player;

/**
 * Created by gergely.szaz on 2016. 10. 16..
 */
public class LoseAction extends AbstractAction {

	public LoseAction() {

		super(null);
	}

	@Override
	public void execute(GameContext context) throws IllegalAccessException {

		Player player = (Player) context.getVariableManager().getReference(null, VariableManager.GLOBAL.CURRENTPLAYER);
		context.getInternalManager().addLoser(player);
	}

}
