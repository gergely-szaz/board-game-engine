package com.github.gergelyszaz.bgs.game.model.action.impl;

import com.github.gergelyszaz.bgs.game.model.action.*;
import com.github.gergelyszaz.bgs.game.*;
import com.github.gergelyszaz.bgs.game.model.Player;

/**
 * Created by gergely.szaz on 2016. 10. 16..
 */
public class EndTurnAction extends AbstractAction {

	public EndTurnAction() {
		super(null);
	}

	@Override
	public void execute(GameContext context) throws IllegalAccessException {

		context.getActionManager().reset();
		Player player = (Player) context.getVariableManager().getReference(null, VariableManager.GLOBAL.CURRENTPLAYER);
		Player nextPlayer = context.getInternalManager().getNextPlayer(player);
		context.getInternalManager().setCurrentPlayer(nextPlayer, context.getVariableManager());
		context.getActionManager().reset();
	}


}
