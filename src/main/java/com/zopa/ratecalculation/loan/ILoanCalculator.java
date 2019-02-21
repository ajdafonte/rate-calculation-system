package com.zopa.ratecalculation.loan;

import com.zopa.ratecalculation.error.RateCalculatorException;


/**
 * ILoanCalculator interface - Describes all methods related with loan calculations.
 */
public interface ILoanCalculator
{
    /**
     * Return the lowest loan repayment details related to the specified {@code loanAmount}.
     *
     * @param filePath - filename path
     * @param loanAmount - loam amount value
     * @return {@code LoanRepaymentDetails} that contains the loan repayment information
     * @throws RateCalculatorException if was not possible to retrieve information about the lenders.
     */
    LoanRepaymentDetails getLowestLoanRepaymentDetails(String filePath, int loanAmount) throws RateCalculatorException;
}
