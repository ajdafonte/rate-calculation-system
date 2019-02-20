package com.zopa.ratecalculation.loan.domain;

import java.util.Objects;


/**
 *
 */
public class LoanRepaymentDetails
{
    private final int requestedAmount;
    private final double rate;
    private final double totalAmount;
    private final double monthlyAmount;

    public static class LoanRepaymentDetailsBuilder
    {
        private int requestedAmount;
        private double rate;
        private double totalAmount;
        private double monthlyAmount;

        public LoanRepaymentDetailsBuilder withRequestedAmount(final int requestedAmount)
        {
            this.requestedAmount = requestedAmount;
            return this;
        }

        public LoanRepaymentDetailsBuilder withRate(final double rate)
        {
            this.rate = rate;
            return this;
        }

        public LoanRepaymentDetailsBuilder withTotalAmount(final double totalAmount)
        {
            this.totalAmount = totalAmount;
            return this;
        }

        public LoanRepaymentDetailsBuilder withMonthlyAmount(final double monthlyAmount)
        {
            this.monthlyAmount = monthlyAmount;
            return this;
        }

        public LoanRepaymentDetails build()
        {
            // call the private constructor in the outer class
            return new LoanRepaymentDetails(this);
        }
    }

    private LoanRepaymentDetails(final LoanRepaymentDetailsBuilder builder)
    {
        this.requestedAmount = builder.requestedAmount;
        this.rate = builder.rate;
        this.totalAmount = builder.totalAmount;
        this.monthlyAmount = builder.monthlyAmount;
    }

    public int getRequestedAmount()
    {
        return requestedAmount;
    }

    public double getRate()
    {
        return rate;
    }

    public double getTotalAmount()
    {
        return totalAmount;
    }

    public double getMonthlyAmount()
    {
        return monthlyAmount;
    }

    @Override
    public boolean equals(final Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        final LoanRepaymentDetails that = (LoanRepaymentDetails) o;
        return requestedAmount == that.requestedAmount &&
            Double.compare(that.rate, rate) == 0 &&
            Double.compare(that.totalAmount, totalAmount) == 0 &&
            Double.compare(that.monthlyAmount, monthlyAmount) == 0;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(requestedAmount, rate, totalAmount, monthlyAmount);
    }

    @Override
    public String toString()
    {
        return "LoanRepaymentDetails{" +
            "requestedAmount=" + requestedAmount +
            ", rate=" + rate +
            ", totalAmount=" + totalAmount +
            ", monthlyAmount=" + monthlyAmount +
            '}';
    }
}
