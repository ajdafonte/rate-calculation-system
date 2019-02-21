package com.zopa.ratecalculation.loan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.zopa.ratecalculation.error.RateCalculatorException;


/**
 * LoanCalculatorTest class - Test LoanCalculator class.
 */
@ExtendWith(MockitoExtension.class)
public class LoanCalculatorTest
{
    @Mock
    private LenderRepository lenderRepository;

    private static final int MOCK_LOAN_DURATION = 36;

    private ILoanCalculator loanCalculator;
    private static final String MOCK_NAME1 = "Bob";
    private static final String MOCK_NAME2 = "Alice";
    private static final String MOCK_NAME3 = "Cesar";
    private static final String MOCK_RATE1 = "0.051";
    private static final String MOCK_RATE2 = "0.082";
    private static final String MOCK_RATE3 = "0.073";
    private static final String MOCK_AMOUNT1 = "1000";
    private static final String MOCK_AMOUNT2 = "750";
    private static final String MOCK_AMOUNT3 = "400";
    private static final Lender MOCK_LENDER1;
    private static final Lender MOCK_LENDER2;
    private static final Lender MOCK_LENDER3;
    private static final List<Lender> MOCK_LENDERS;

    static
    {
        MOCK_LENDER1 = generateLender(MOCK_NAME1, Double.parseDouble(MOCK_RATE1), Double.parseDouble(MOCK_AMOUNT1));
        MOCK_LENDER2 = generateLender(MOCK_NAME2, Double.parseDouble(MOCK_RATE2), Double.parseDouble(MOCK_AMOUNT2));
        MOCK_LENDER3 = generateLender(MOCK_NAME3, Double.parseDouble(MOCK_RATE3), Double.parseDouble(MOCK_AMOUNT3));
        MOCK_LENDERS = Arrays.asList(MOCK_LENDER1, MOCK_LENDER2, MOCK_LENDER3);
    }

    private static Lender generateLender(final String name, final double rate, final double availableAmount)
    {
        return new Lender.LenderBuilder()
            .withName(name)
            .withRate(rate)
            .withAvailableAmount(availableAmount)
            .build();
    }

    @BeforeEach
    public void setUp()
    {
        this.loanCalculator = new LoanCalculator(lenderRepository, MOCK_LOAN_DURATION);
    }

    // test ok data case
    @Test
    public void givenValidArgs_whenGetLowestLoanRepayment_thenReturnCorrectLowestLoanRepaymentDetails() throws IOException
    {
        // given
        final String mockFilePath = "mock.csv";
        final int mockLoanAmount = 700;
        final double mockRate = 5.1;
        final double mockMonthlyAmount = 21.01107085987861;
        final double mockTotalAmount = 756.39855095563;
        Mockito.when(lenderRepository.findAll(mockFilePath)).thenReturn(MOCK_LENDERS);
        final LoanRepaymentDetails expectedResult = new LoanRepaymentDetails.LoanRepaymentDetailsBuilder()
            .withRequestedAmount(mockLoanAmount)
            .withRate(mockRate)
            .withMonthlyAmount(mockMonthlyAmount)
            .withTotalAmount(mockTotalAmount)
            .build();

        // when
        final LoanRepaymentDetails result = loanCalculator.getLowestLoanRepaymentDetails(mockFilePath, mockLoanAmount);

        // then
        assertNotNull(result);
        assertEquals(expectedResult, result);
        assertEquals(expectedResult.getRequestedAmount(), mockLoanAmount);
        assertEquals(expectedResult.getMonthlyAmount(), mockMonthlyAmount);
        assertEquals(expectedResult.getRate(), mockRate);
        assertEquals(expectedResult.getTotalAmount(), mockTotalAmount);
    }

    // test nok data (file path)
    @Test
    public void givenUnknownFile_whenGetLowestLoanRepayment_thenThrowSpecificException() throws IOException
    {
        // given
        final String mockFilePath = "unknown.csv";
        final int mockLoanAmount = 1000;
        Mockito.when(lenderRepository.findAll(mockFilePath)).thenThrow(IOException.class);

        // when + then
        assertThrows(RateCalculatorException.class, () -> loanCalculator.getLowestLoanRepaymentDetails(mockFilePath, mockLoanAmount));
    }

    // test ok input - no lender for loan amount
    @Test
    public void givenLendersWithNotAvailableAmount_whenGetLowestLoanRepayment_thenReturnNullValue() throws IOException
    {
        // given
        final String mockFilePath = "mock.csv";
        final int mockLoanAmount = 10000;
        Mockito.when(lenderRepository.findAll(mockFilePath)).thenReturn(MOCK_LENDERS);

        // when
        final LoanRepaymentDetails result = loanCalculator.getLowestLoanRepaymentDetails(mockFilePath, mockLoanAmount);

        // then
        assertNull(result);
    }

    // test ok input - no lenders from market
    @Test
    public void givenNoLendersAvailable_whenGetLowestLoanRepayment_thenReturnNullValue() throws IOException
    {
        // given
        final String mockFilePath = "mock.csv";
        final int mockLoanAmount = 1000;
        Mockito.when(lenderRepository.findAll(mockFilePath)).thenReturn(Collections.emptyList());

        // when
        final LoanRepaymentDetails result = loanCalculator.getLowestLoanRepaymentDetails(mockFilePath, mockLoanAmount);

        // then
        assertNull(result);
    }
}
