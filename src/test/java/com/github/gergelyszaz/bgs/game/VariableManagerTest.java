package com.github.gergelyszaz.bgs.game;

import com.github.gergelyszaz.bgl.bgl.*;
import org.eclipse.emf.common.util.*;
import org.junit.*;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class VariableManagerTest {

    VariableManager variableManager;
    ArithmeticManager arithmeticManager;
    Object firstObject = "firstObject";
    Object secondObject = "secondObject";
    Object thirdObject = "thirdObject";

    @Before
    public void setup() {

        variableManager = new VariableManager();
        arithmeticManager = new ArithmeticManager(variableManager);

        variableManager.store(null, "first", firstObject);

        variableManager.store(firstObject, "second", secondObject);

        variableManager.store(secondObject, "third", thirdObject);

        variableManager.store(null, "zero", 0);

        variableManager.store(firstObject, "one", 1);
        variableManager.store(secondObject, "two", 2);

    }

    @Test
    public void GetValueUsingNameTest() {

        int zero = variableManager.getValue(null, "zero");
        int one = variableManager.getValue(firstObject, "one");

        assertEquals(0, zero);
        assertEquals(1, one);

    }

    @Test(expected = IllegalAccessError.class)
    public void GetValueUsingInvalidNameTest() {

        variableManager.getValue(null, "invalid");
    }

    @Test
    public void GetValueTest() {

        Object one = variableManager.getReference("first.one");

        assertEquals(1, one);
    }


    @Test
    public void StoreUsingNameStringTest() {

        Object object = new Object();
        variableManager.store(null, "newName", object);

        assertEquals(object, variableManager.getReference(null, "newName"));
    }

    @Test
    public void StoreUsingPathTest() {
        Object object = new Object();
        String path = "first.second.third.fourth";
        variableManager.store(path, object);

        assertEquals(object, variableManager.getReference(path));
    }

    @Test(expected = IllegalAccessError.class)
    public void StoreUsingInvalidPathTest() {

        String path = "first.second.invalid.fourth";

        Object object = new Object();
        variableManager.store(path, object);
        assertEquals(object, variableManager.getReference(path));

    }

    //ArithmeticManager tests

    @Test
    public void EvaluateBooleanExpTest() throws Exception {

        AttributeOrInt attributeOrInt5 = mock(AttributeOrInt.class);
        AttributeOrInt attributeOrInt6 = mock(AttributeOrInt.class);
        AttributeOrInt attributeOrIntObject1 = mock(AttributeOrInt.class);
        AttributeOrInt attributeOrIntObject2 = mock(AttributeOrInt.class);
        AttributeName attributeName1 = mock(AttributeName.class);
        AttributeName attributeName2 = mock(AttributeName.class);

        when(attributeOrInt5.getValue()).thenReturn(5);
        when(attributeOrInt6.getValue()).thenReturn(6);
        when(attributeOrIntObject1.getAttribute()).thenReturn(attributeName1);
        when(attributeOrIntObject2.getAttribute()).thenReturn(attributeName2);
        when(attributeName1.getName()).thenReturn("first");
        when(attributeName2.getName()).thenReturn("zero");

        assertTrue(EvaluateBooleanExp(null, attributeOrInt5, "<",
                attributeOrInt6));
        assertFalse(
                EvaluateBooleanExp(null, attributeOrInt6, "<", attributeOrInt5));
        assertFalse(
                EvaluateBooleanExp(null, attributeOrInt6, "<", attributeOrInt6));

        assertTrue(EvaluateBooleanExp(null, attributeOrInt6, ">",
                attributeOrInt5));
        assertFalse(EvaluateBooleanExp(null, attributeOrInt5, ">",
                attributeOrInt6));
        assertFalse(EvaluateBooleanExp(null, attributeOrInt6, ">",
                attributeOrInt6));

        assertTrue(
                EvaluateBooleanExp(null, attributeOrInt5, "<=", attributeOrInt6));
        assertFalse(
                EvaluateBooleanExp(null, attributeOrInt6, "<=", attributeOrInt5));
        assertTrue(
                EvaluateBooleanExp(null, attributeOrInt6, "<=", attributeOrInt6));

        assertFalse(
                EvaluateBooleanExp(null, attributeOrInt5, ">=", attributeOrInt6));
        assertTrue(
                EvaluateBooleanExp(null, attributeOrInt6, ">=", attributeOrInt5));
        assertTrue(
                EvaluateBooleanExp(null, attributeOrInt6, ">=", attributeOrInt6));

        assertTrue(
                EvaluateBooleanExp(null, attributeOrInt5, "==", attributeOrInt5));
        assertFalse(
                EvaluateBooleanExp(null, attributeOrInt5, "==", attributeOrInt6));

        assertTrue(EvaluateBooleanExp(null, attributeOrIntObject1, "===",
                attributeOrIntObject1));
        assertFalse(EvaluateBooleanExp(null, attributeOrIntObject1, "===",
                attributeOrIntObject2));

        assertFalse(EvaluateBooleanExp(null, attributeOrIntObject1, "!==",
                attributeOrIntObject1));
        assertTrue(EvaluateBooleanExp(null, attributeOrIntObject1, "!==",
                attributeOrIntObject2));

        assertTrue(
                EvaluateBooleanExp("NOT", attributeOrInt6, "<", attributeOrInt5));
    }

    @Test
    public void EvaluateAndExpTest() throws Exception {

        assertTrue(EvaluateAndExp(true, true));
        assertFalse(EvaluateAndExp(false, true));
        assertFalse(EvaluateAndExp(true, false));
        assertFalse(EvaluateAndExp(false, false));
    }

    @Test
    public void EvaluateOrExpTest() throws Exception {

        assertTrue(EvaluateOrExp(true, true));
        assertTrue(EvaluateOrExp(true, false));
        assertTrue(EvaluateOrExp(false, true));
        assertFalse(EvaluateOrExp(false, false));
    }

    @Test
    public void GetReferenceUsingPathTest() {

        String path = "first.second.third";
        Object reference = variableManager.getReference(path);
        assertEquals(thirdObject, reference);
    }

    @Test(expected = IllegalAccessError.class)
    public void GetReferenceUsingInvalidPathTest() {

        String path = "first.second.invalid.fourth";
        variableManager.getReference(path);
    }

    @Test
    public void GetReferenceUsingNameTest() {

        Object first = variableManager.getReference(null, "first");
        Object second = variableManager.getReference(firstObject, "second");
        assertEquals(firstObject, first);
        assertEquals(secondObject, second);
    }

    @Test(expected = IllegalAccessError.class)
    public void GetReferenceUsingInvalidNameTest() {

        variableManager.getReference(null, "invalid");
    }

    private boolean EvaluateBooleanExp(
            String not, AttributeOrInt left, String operator, AttributeOrInt right) {

        BooleanExp booleanExp = mock(BooleanExp.class);

        when(booleanExp.getNot()).thenReturn(not);
        when(booleanExp.getName()).thenReturn(operator);
        when(booleanExp.getLeft()).thenReturn(left);
        when(booleanExp.getRight()).thenReturn(right);

        return arithmeticManager.evaluate(booleanExp);
    }

    private boolean EvaluateAndExp(boolean left, boolean right) {

        ArithmeticManager spy = spy(arithmeticManager);

        BooleanExp booleanExp1 = mock(BooleanExp.class);
        BooleanExp booleanExp2 = mock(BooleanExp.class);
        AndExp andExp = mock(AndExp.class);
        EList<BooleanExp> booleanExps =
                new BasicEList<>(Arrays.asList(booleanExp1, booleanExp2));

        when(andExp.getExpressions()).thenReturn(booleanExps);

        doReturn(left).when(spy).evaluate(booleanExp1);
        doReturn(right).when(spy).evaluate(booleanExp2);

        return spy.evaluate(andExp);
    }

    private boolean EvaluateOrExp(boolean left, boolean right) {

        ArithmeticManager spy = spy(arithmeticManager);

        OrExp orExp = mock(OrExp.class);
        AndExp andExp1 = mock(AndExp.class);
        AndExp andExp2 = mock(AndExp.class);
        EList<AndExp> andExps = new BasicEList<>(Arrays.asList(andExp1, andExp2));

        when(orExp.getExpressions()).thenReturn(andExps);

        doReturn(left).when(spy).evaluate(andExp1);
        doReturn(right).when(spy).evaluate(andExp2);

        return spy.evaluate(orExp);
    }

}