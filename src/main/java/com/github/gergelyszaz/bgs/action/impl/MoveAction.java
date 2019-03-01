package com.github.gergelyszaz.bgs.action.impl;

import com.github.gergelyszaz.bgs.action.AbstractAction;
import com.github.gergelyszaz.bgs.game.*;
import com.github.gergelyszaz.bgs.game.internal.Card;
import com.github.gergelyszaz.bgs.game.internal.Deck;
import com.github.gergelyszaz.bgs.game.internal.Token;
import com.github.gergelyszaz.bgl.bgl.*;

import java.util.Objects;

/**
 * Created by gergely.szaz on 2016. 10. 16..
 */
public class MoveAction extends AbstractAction {

    BGLUtil bglUtil = new BGLUtil();

    public MoveAction(VariableManager variableManager, com.github.gergelyszaz.bgl.bgl.Action action) {
        super(variableManager,action);
    }

    @Override
    public void Execute() throws IllegalAccessException {
        if (Objects.equals(action.getType(), "CARD")) {
            ((Card) variableManager.getReference(bglUtil.toString(action.getSelected())))
                    .MoveTo((Deck) variableManager.getReference(bglUtil.toString(action
                          .getMoveTo())));
        }
        else {
            ((Token) variableManager.getReference(bglUtil.toString(action.getSelected())))
                  .setField((Field) variableManager.getReference(bglUtil.toString(
                        action.getMoveTo())));
        }
    }
}
