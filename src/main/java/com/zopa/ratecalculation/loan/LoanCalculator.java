package com.zopa.ratecalculation.loan;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import com.zopa.ratecalculation.error.RateCalculatorException;
import com.zopa.ratecalculation.loan.domain.Lender;
import com.zopa.ratecalculation.loan.domain.LoanRepaymentDetails;
import com.zopa.ratecalculation.loan.repo.LenderRepository;
import com.zopa.ratecalculation.util.Util;


/**
 *
 */
public class LoanCalculator implements ILoanCalculator
{
    private final LenderRepository lenderRepository;
    private final int loanDuration;

    public LoanCalculator(final LenderRepository lenderRepository, final int loanDuration)
    {
        this.lenderRepository = lenderRepository;
        this.loanDuration = loanDuration;
    }

    @Override
    public LoanRepaymentDetails getLowestLoanRepaymentDetails(final String filePath, final int loanAmount) throws RateCalculatorException
    {
        try
        {
            // get all lenders available in market
            final List<Lender> lenders = lenderRepository.findAll(filePath);
//            final List<Lender> lenders = LenderMapper.mapFromReader(reader.readDataFile(filePath, true));

            // get lender with lowest rate
            final Lender lender = getLenderWithLowestRateForLoan(lenders, loanAmount);

            // retrieve lowest loan repayment details
            return lender != null ? calculateLowestLoanRepaymentDetail(loanAmount, loanDuration, lender) : null;
        }
        catch (final IOException e)
        {
            throw new RateCalculatorException("Unable to read data from " + filePath);
        }
    }

    private LoanRepaymentDetails calculateLowestLoanRepaymentDetail(final int loanAmount, final int loanDuration, final Lender lender)
    {
        final double lenderInterestRate = Util.toPercentageValue(lender.getRate());
        final double monthlyAmount = Util.pmt(lenderInterestRate, loanDuration, loanAmount);
        final double totalAmount = monthlyAmount * loanDuration;

        return new LoanRepaymentDetails.LoanRepaymentDetailsBuilder()
            .withRequestedAmount(loanAmount)
            .withRate(lenderInterestRate)
            .withMonthlyAmount(monthlyAmount)
            .withTotalAmount(totalAmount)
            .build();
    }

    private Lender getLenderWithLowestRateForLoan(final List<Lender> lenders, final int loanAmount)
    {
        return lenders.stream()
            .filter(lenderData -> lenderData.getAvailableAmount() >= loanAmount)
            .min(Comparator.comparing(Lender::getRate))
            .orElse(null);
    }
}
