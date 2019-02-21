package com.zopa.ratecalculation.loan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;


/**
 * LoanRepaymentDetailsTest class - Test LoanRepaymentDetails class.
 */
public class LoanRepaymentDetailsTest
{
    private static final double MOCK_RATE1 = 5.1;
    private static final double MOCK_TOTAL_AMOUNT1 = 756.39855095563;
    private static final double MOCK_MONTHLY_AMOUNT1 = 21.01107085987861;
    private static final int MOCK_AMOUNT1 = 700;
    private static final int MOCK_AMOUNT2 = 2000;

    private static LoanRepaymentDetails generateLoanRepaymentDetails(final int amount)
    {
        return new LoanRepaymentDetails.LoanRepaymentDetailsBuilder()
            .withRequestedAmount(amount)
            .withRate(LoanRepaymentDetailsTest.MOCK_RATE1)
            .withMonthlyAmount(LoanRepaymentDetailsTest.MOCK_MONTHLY_AMOUNT1)
            .withTotalAmount(LoanRepaymentDetailsTest.MOCK_TOTAL_AMOUNT1)
            .build();
    }

    @Test
    public void givenTwoEqualLoanRepaymentDetails_whenCheckIfEquals_thenReturnOk()
    {
        // given
        final LoanRepaymentDetails mockRepaymentDetails1 =
            generateLoanRepaymentDetails(MOCK_AMOUNT1);
        final LoanRepaymentDetails mockRepaymentDetails2 =
            generateLoanRepaymentDetails(MOCK_AMOUNT1);

        assertEquals(mockRepaymentDetails1.hashCode(), mockRepaymentDetails2.hashCode());
        assertEquals(mockRepaymentDetails1, mockRepaymentDetails2);
    }

    @Test
    public void givenTwoDifferentLoanRepaymentDetails_whenCheckIfEquals_thenReturnNok()
    {
        // given
        final LoanRepaymentDetails mockRepaymentDetails1 =
            generateLoanRepaymentDetails(MOCK_AMOUNT1);
        final LoanRepaymentDetails mockRepaymentDetails2 =
            generateLoanRepaymentDetails(MOCK_AMOUNT2);

        assertNotEquals(mockRepaymentDetails1.hashCode(), mockRepaymentDetails2.hashCode());
        assertNotEquals(mockRepaymentDetails1, mockRepaymentDetails2);
    }

    @Test
    public void givenLoanRepaymentDetails_whenCallToString_thenReturnExpectedValue()
    {
        // given
        final LoanRepaymentDetails mockRepaymentDetails =
            generateLoanRepaymentDetails(MOCK_AMOUNT1);
        final String expected = "LoanRepaymentDetails{" +
            "requestedAmount=" + MOCK_AMOUNT1 +
            ", rate=" + MOCK_RATE1 +
            ", totalAmount=" + MOCK_TOTAL_AMOUNT1 +
            ", monthlyAmount=" + MOCK_MONTHLY_AMOUNT1 +
            '}';

        // when
        final String value = mockRepaymentDetails.toString();

        // then
        assertEquals(expected, value);
    }
}
