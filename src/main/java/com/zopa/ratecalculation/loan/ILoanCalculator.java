package com.zopa.ratecalculation.loan;

import com.zopa.ratecalculation.error.RateCalculatorException;
import com.zopa.ratecalculation.loan.domain.LoanRepaymentDetails;


/**
 *
 */
public interface ILoanCalculator
{
    /**
     * @param filePath
     * @param loanAmount
     * @return
     * @throws RateCalculatorException
     */
    LoanRepaymentDetails getLowestLoanRepaymentDetails(String filePath, int loanAmount) throws RateCalculatorException;
}
