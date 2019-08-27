package com.github.gergelyszaz.bgs.game.model.action.impl;

import com.github.gergelyszaz.bgl.bgl.*;
import com.github.gergelyszaz.bgl.bgl.Field;

import com.github.gergelyszaz.bgs.game.model.action.AbstractAction;
import com.github.gergelyszaz.bgs.game.*;
import com.github.gergelyszaz.bgs.game.model.Player;
import com.github.gergelyszaz.bgs.game.model.Token;

import java.util.List;

/**
 * Created by Gergely Száz
 */
public class SpawnAction extends AbstractAction {

	
	private final String tokenTypeName;
	private final List<SimpleAssignment> variables;
	private final AttributeName fieldName;
	private final AttributeName tokenName;
	private BGLUtil bglUtil = new BGLUtil();

	public SpawnAction(Action action) {

		super(action);
		tokenTypeName = action.getToken().getName();
		variables = action.getToken().getVariables();
		fieldName = action.getSpawnTo();
		tokenName = action.getToVar();
	}

	@Override
	public void execute(GameContext context) throws IllegalAccessException {

		Token token = new Token(context.getVariableManager(), tokenTypeName);
		setupVariables(token, context);
		addTokenToVariableManager(token, context);
		context.getInternalManager().addToken(token);
	}

	private void setupVariables(Token token, GameContext context) throws IllegalAccessException {

		for (SimpleAssignment a : variables) {
			String variableName = a.getName();
			Object reference = a.getAttribute().getValue();
			context.getVariableManager().store(token, variableName, reference);
		}
		token.setOwner((Player) context.getVariableManager().getReference(null, "currentPlayer"));
		token.setField((Field) context.getVariableManager().getReference(bglUtil.toString(fieldName)));
	}

	private void addTokenToVariableManager(Token token, GameContext context) throws IllegalAccessException {

		String variablePath = bglUtil.toString(tokenName);
		context.getVariableManager().store(variablePath, token);
	}
}
