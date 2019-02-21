package com.zopa.ratecalculation.error;

/**
 * RateCalculatorException exception - Generic RateCalculator exception.
 */
public class RateCalculatorException extends RuntimeException
{
    private static final long serialVersionUID = 5664154152263389406L;

    public RateCalculatorException(final String message)
    {
        super(message);
    }
}
