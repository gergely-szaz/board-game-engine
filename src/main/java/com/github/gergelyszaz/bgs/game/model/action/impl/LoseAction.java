package com.github.gergelyszaz.bgs.game.model.action.impl;

import com.github.gergelyszaz.bgs.game.model.action.AbstractAction;
import com.github.gergelyszaz.bgs.game.*;
import com.github.gergelyszaz.bgs.game.model.Player;

/**
 * Created by gergely.szaz on 2016. 10. 16..
 */
public class LoseAction extends AbstractAction {

	private final InternalManager internalManager;

	public LoseAction(VariableManager variableManager,
							InternalManager internalManager) {

		super(variableManager, null);
		this.internalManager = internalManager;
	}

	@Override
	public void Execute() throws IllegalAccessException {

		Player player = (Player) variableManager.getReference(null,
				VariableManager.GLOBAL.CURRENTPLAYER);
		internalManager.addLoser(player);
	}

}
