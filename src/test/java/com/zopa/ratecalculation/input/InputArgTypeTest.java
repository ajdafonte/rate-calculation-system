package com.zopa.ratecalculation.input;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


/**
 *
 */
public class InputArgTypeTest
{
    // test position values
    @Test
    public void givenEnumValues_whenGetPosition_returnCorrectPosition()
    {
        assertEquals(0, InputArgType.FILEPATH.getPosition());
        assertEquals(1, InputArgType.LOAN_AMOUNT.getPosition());
    }

    // test method that return amount
    @Test
    public void givenTotalEnumValues_whenGetNumberOfArgs_returnCorrectValue()
    {
        assertEquals(2, InputArgType.getNumberOfArgs());
    }
}
