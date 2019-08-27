package com.github.gergelyszaz.bgs.game.model.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.github.gergelyszaz.bgl.bgl.Action;
import com.github.gergelyszaz.bgs.game.model.action.impl.AssignmentAction;
import com.github.gergelyszaz.bgs.game.model.action.impl.ConditionalAction;
import com.github.gergelyszaz.bgs.game.model.action.impl.DestroyAction;
import com.github.gergelyszaz.bgs.game.model.action.impl.EndTurnAction;
import com.github.gergelyszaz.bgs.game.model.action.impl.GotoAction;
import com.github.gergelyszaz.bgs.game.model.action.impl.IfAction;
import com.github.gergelyszaz.bgs.game.model.action.impl.LoseAction;
import com.github.gergelyszaz.bgs.game.model.action.impl.MoveAction;
import com.github.gergelyszaz.bgs.game.model.action.impl.NopAction;
import com.github.gergelyszaz.bgs.game.model.action.impl.RollAction;
import com.github.gergelyszaz.bgs.game.model.action.impl.SelectAction;
import com.github.gergelyszaz.bgs.game.model.action.impl.ShuffleAction;
import com.github.gergelyszaz.bgs.game.model.action.impl.SpawnAction;
import com.github.gergelyszaz.bgs.game.model.action.impl.WhileAction;
import com.github.gergelyszaz.bgs.game.model.action.impl.WinAction;

/**
 * Created by gergely.szaz on 2016. 10. 16..
 */
public class ActionFactory {

	public static final String DESTROY = "DESTROY";
	public static final String IF = "IF";
	public static final String WHILE = "WHILE";
	public static final String ENDTURN = "END TURN";
	public static final String MOVE = "MOVE";
	public static final String LOSE = "LOSE";
	public static final String WIN = "WIN";
	public static final String ROLL = "ROLL";
	public static final String SELECT = "SELECT";
	public static final String SHUFFLE = "SHUFFLE";
	public static final String SPAWN = "SPAWN";
	public static final String ASSIGNMENT = "ASSIGNMENT";

	public List<ConcreteAction> createActionSequence(List<Action> actions) throws IllegalAccessException {

		List<ConcreteAction> concreteActions = new ArrayList<>();

		for (Action action : actions) {
			concreteActions.addAll(_createActionSequence(action));
		}

		if (actions.size() == 0) {
			concreteActions.add(new NopAction());
		}

		return concreteActions;
	}

	public ConcreteAction createAction(Action action)
		 throws IllegalAccessException {

		ConcreteAction returnAction = null;
		String actionName=action.getName();
		if(actionName==null)
			actionName=ASSIGNMENT;
		switch (actionName) {
			case SELECT:
				returnAction =
					 new SelectAction(action
						  );
				break;
			case SPAWN:
				returnAction =
					 new SpawnAction(action);
				break;
			case MOVE:
				returnAction = new MoveAction(action);
				break;
			case SHUFFLE:
				returnAction = new ShuffleAction(action);
				break;
			case DESTROY:
				returnAction =
					 new DestroyAction(action);
				break;
			case WIN:
				returnAction = new WinAction();
				break;
			case LOSE:
				returnAction = new LoseAction();
				break;
			case IF:
				returnAction = new IfAction(action);
				break;
			case WHILE:
				returnAction =
					 new WhileAction(action);
				break;
			case ENDTURN:
				returnAction =
					 new EndTurnAction();
				break;
			case ROLL:
				returnAction = new RollAction(action);
				break;
			case ASSIGNMENT:
				returnAction = new AssignmentAction(action);
				break;
			default:
				break;
		}
		return returnAction;

	}

	private List<ConcreteAction> _createActionSequence(Action action) throws IllegalAccessException {

		List<ConcreteAction> returnActions = new ArrayList<>();
		ConcreteAction newAction = createAction(action);
		returnActions.add(newAction);

		if (action.getNestedAction() != null) {
			returnActions.addAll(createActionSequence(action.getNestedAction().getActions()));

			if (Objects.equals(action.getName(), WHILE)) {
				returnActions.add(new GotoAction(newAction));
			}

			ConcreteAction nopAction = new NopAction();
			returnActions.add(nopAction);
			((ConditionalAction) newAction).setSkipAction(nopAction);

		}

		return returnActions;
	}
}
