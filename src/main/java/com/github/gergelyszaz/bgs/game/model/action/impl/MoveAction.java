package com.github.gergelyszaz.bgs.game.model.action.impl;

import com.github.gergelyszaz.bgs.game.model.action.AbstractAction;
import com.github.gergelyszaz.bgs.game.*;
import com.github.gergelyszaz.bgs.game.model.Card;
import com.github.gergelyszaz.bgs.game.model.Deck;
import com.github.gergelyszaz.bgs.game.model.Token;
import com.github.gergelyszaz.bgl.bgl.*;


import java.util.Objects;

/**
 * Created by gergely.szaz on 2016. 10. 16..
 */
public class MoveAction extends AbstractAction {

    BGLUtil bglUtil = new BGLUtil();

    public MoveAction(Action action) {
        super(action);
    }

    @Override
    public void execute(GameContext context) throws IllegalAccessException {
        if (Objects.equals(action.getType(), "CARD")) {
            ((Card) context.getVariableManager().getReference(bglUtil.toString(action.getSelected())))
                    .MoveTo((Deck) context.getVariableManager().getReference(bglUtil.toString(action.getMoveTo())));
        } else {
            ((Token) context.getVariableManager().getReference(bglUtil.toString(action.getSelected())))
                    .setField((Field) context.getVariableManager().getReference(bglUtil.toString(action.getMoveTo())));
        }
    }
}
