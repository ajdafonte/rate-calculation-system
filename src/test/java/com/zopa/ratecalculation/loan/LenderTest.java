package com.zopa.ratecalculation.loan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import com.zopa.ratecalculation.loan.domain.Lender;


/**
 *
 */
public class LenderTest
{
    private static final String MOCK_NAME1 = "Bob";
    private static final String MOCK_NAME2 = "Alice";
    private static final double MOCK_RATE1 = 0.051;
    private static final double MOCK_AMOUNT1 = 1000;

    private static Lender generateLender(final String name, final double rate, final double availableAmount)
    {
        return new Lender.LenderBuilder()
            .withName(name)
            .withRate(rate)
            .withAvailableAmount(availableAmount)
            .build();
    }

    @Test
    public void givenTwoEqualLenders_whenCheckIfEquals_thenReturnTrue()
    {
        // given
        final Lender mockLender1 = generateLender(MOCK_NAME1, MOCK_RATE1, MOCK_AMOUNT1);
        final Lender mockLender2 = generateLender(MOCK_NAME1, MOCK_RATE1, MOCK_AMOUNT1);

        assertEquals(mockLender1.hashCode(), mockLender2.hashCode());
        assertEquals(mockLender1, mockLender2);
    }

    @Test
    public void givenTwoDifferentLenders_whenCheckIfEquals_thenReturnFalse()
    {
        // given
        final Lender mockLender1 = generateLender(MOCK_NAME1, MOCK_RATE1, MOCK_AMOUNT1);
        final Lender mockLender2 = generateLender(MOCK_NAME2, MOCK_RATE1, MOCK_AMOUNT1);

        assertNotEquals(mockLender1.hashCode(), mockLender2.hashCode());
        assertNotEquals(mockLender1, mockLender2);
    }

    @Test
    public void givenLender_whenCallToString_thenReturnExpectedValue()
    {
        // given
        final Lender mockLender1 = generateLender(MOCK_NAME1, MOCK_RATE1, MOCK_AMOUNT1);
        final String expected = "LenderData{" +
            "name='" + MOCK_NAME1 + '\'' +
            ", rate=" + MOCK_RATE1 +
            ", availableAmount=" + MOCK_AMOUNT1 +
            '}';

        // when
        final String value = mockLender1.toString();

        // then
        assertEquals(expected, value);
    }
}
