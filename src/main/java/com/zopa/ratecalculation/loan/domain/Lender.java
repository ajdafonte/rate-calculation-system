package com.zopa.ratecalculation.loan.domain;

import java.util.Objects;


/**
 *
 */
public class Lender
{
    private final String name;
    private final double rate;
    private final double availableAmount;

    public static class LenderBuilder
    {
        private String name;
        private double rate;
        private double availableAmount;

        public LenderBuilder withName(final String name)
        {
            this.name = name;
            return this;
        }

        public LenderBuilder withRate(final double rate)
        {
            this.rate = rate;
            return this;
        }

        public LenderBuilder withAvailableAmount(final double availableAmount)
        {
            this.availableAmount = availableAmount;
            return this;
        }

        public Lender build()
        {
            // call the private constructor in the outer class
            return new Lender(this);
        }
    }

    private Lender(final LenderBuilder builder)
    {
        this.name = builder.name;
        this.rate = builder.rate;
        this.availableAmount = builder.availableAmount;
    }

    public double getRate()
    {
        return rate;
    }

    public double getAvailableAmount()
    {
        return availableAmount;
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
        final Lender lender = (Lender) o;
        return Double.compare(lender.rate, rate) == 0 &&
            Double.compare(lender.availableAmount, availableAmount) == 0 &&
            name.equals(lender.name);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name, rate, availableAmount);
    }

    @Override
    public String toString()
    {
        return "LenderData{" +
            "name='" + name + '\'' +
            ", rate=" + rate +
            ", availableAmount=" + availableAmount +
            '}';
    }
}
