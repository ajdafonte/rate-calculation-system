package com.zopa.ratecalculation;

import com.zopa.ratecalculation.error.InputArgException;
import com.zopa.ratecalculation.error.RateCalculatorException;
import com.zopa.ratecalculation.input.InputArgHandler;
import com.zopa.ratecalculation.loan.ILoanCalculator;
import com.zopa.ratecalculation.loan.domain.LoanRepaymentDetails;


/**
 *
 */
class RateCalculatorSystem
{
    private final InputArgHandler inputArgHandler;
    private final ILoanCalculator loanCalculator;
    private final String currencySymbol;

    RateCalculatorSystem(final InputArgHandler inputArgHandler, final ILoanCalculator loanCalculator,
                         final String currencySymbol)
    {
        this.inputArgHandler = inputArgHandler;
        this.loanCalculator = loanCalculator;
        this.currencySymbol = currencySymbol;
    }

    void start(final String[] args)
    {
        try
        {
            // validate input args
            inputArgHandler.validateInputArgs(args);

            // get valid args
            final String filePath = inputArgHandler.getFilePath(args);
            final String loanAmount = inputArgHandler.getLoanAmount(args);

            // calculate and show LowestLoanRepayment
            final LoanRepaymentDetails loanRepaymentDetails =
                loanCalculator.getLowestLoanRepaymentDetails(filePath, Integer.parseInt(loanAmount));
            showResults(currencySymbol, loanRepaymentDetails);
        }
        catch (final InputArgException ex)
        {
            System.err.println(ex.getMessage());
            System.out.println(inputArgHandler.generateStartupHelpMessage());
        }
        catch (final RateCalculatorException ex)
        {
            System.err.println(ex.getMessage());
        }
    }

    private void showResults(final String currencySymbol, final LoanRepaymentDetails repaymentResult)
    {
        if (repaymentResult != null)
        {
            System.out.printf("Requested amount: %s%d %n", currencySymbol, repaymentResult.getRequestedAmount());
            System.out.printf("Rate: %.1f%% %n", repaymentResult.getRate());
            System.out.printf("Monthly repayment: %s%.2f %n", currencySymbol, repaymentResult.getMonthlyAmount());
            System.out.printf("Total repayment: %s%.2f %n", currencySymbol, repaymentResult.getTotalAmount());
        }
        else
        {
            System.out.println(
                "Sorry, but unfortunately it was not possible to provide the lowest loan repayment details for the loan amount indicated");
        }
    }

}
