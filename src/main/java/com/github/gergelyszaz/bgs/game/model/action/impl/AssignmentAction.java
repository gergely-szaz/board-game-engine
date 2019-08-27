package com.github.gergelyszaz.bgs.game.model.action.impl;

import com.github.gergelyszaz.bgl.bgl.*;
import com.github.gergelyszaz.bgs.game.model.action.AbstractAction;
import com.github.gergelyszaz.bgs.game.*;

import java.util.List;

/**
 * Created by Gergely Száz
 */
public class AssignmentAction extends AbstractAction {
    private BGLUtil bglUtil = new BGLUtil();

    public AssignmentAction(Action action) {
        super(action);
    }

    @Override
    public void execute(GameContext context) throws IllegalAccessException {

        VariableManager variableManager = context.getVariableManager();

        ValueAssignment assignment = action.getAssignment();
        Object reference = getReference(assignment.getExpression(), variableManager);

        String variablePath = bglUtil.toString(assignment.getName());

        variableManager.store(variablePath, reference);
    }

    @Override
    public String toString() {

        return super.toString();
    }

    private Object getReference(ArithmeticExp arithmeticExp, VariableManager variableManager) {
        ArithmeticManager arithmeticManager = new ArithmeticManager(variableManager);

        // just a simple reference
        List<Expression> expressions = arithmeticExp.getExpressions();
        if (expressions.size() == 1 && expressions.get(0).getAttributeOrInt() != null) {
            AttributeOrInt attributeOrInt = expressions.get(0).getAttributeOrInt();
            return arithmeticManager.resolveReference(attributeOrInt);
        }

        // an expression
        int i = 0;
        int value = getReference(arithmeticExp.getExpressions().get(i), variableManager);
        for (String operator : arithmeticExp.getOperators()) {
            i++;

            int expressionValue = getReference(expressions.get(i), variableManager);
            switch (operator) {
            case "+":
                value += expressionValue;
                break;
            case "-":
                value -= expressionValue;
                break;
            case "%":
                value %= expressionValue;
                break;
            case "*":
                value *= expressionValue;
                break;
            case "/":
                value /= expressionValue;
                break;
            }
        }
        return value;
    }

    private int getReference(Expression expression, VariableManager variableManager) {
        Object reference = null;
        if (expression.getAttributeOrInt() != null) {
            ArithmeticManager arithmeticManager = new ArithmeticManager(variableManager);
            reference = arithmeticManager.resolveReference(expression.getAttributeOrInt());
        }
        if (expression.getArithmeticExp() != null) {
            reference = getReference(expression.getArithmeticExp(), variableManager);
        }

        if (!(reference instanceof Integer)) {
            throw new IllegalAccessError("Could not get value of" + reference);
        }
        return (Integer) reference;
    }
}
