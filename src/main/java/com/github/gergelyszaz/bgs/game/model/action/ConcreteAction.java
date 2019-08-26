package com.github.gergelyszaz.bgs.game.model.action;

import com.github.gergelyszaz.bgs.game.GameContext;

/**
 * Created by mad on 2016. 10. 12..
 */
public interface ConcreteAction {
	void execute(GameContext context) throws IllegalAccessException;
}
