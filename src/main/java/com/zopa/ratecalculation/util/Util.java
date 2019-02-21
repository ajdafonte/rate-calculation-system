package com.zopa.ratecalculation.util;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;


/**
 * Util class - Contains some utility functions.
 */
public class Util
{
    public static double toDouble(final String doubleVal)
    {
        try
        {
            return Double.parseDouble(doubleVal);
        }
        catch (final NumberFormatException ex)
        {
            return 0.0;
        }
    }

    public static double toPercentageValue(final double value)
    {
        return value * 100;
    }

    /**
     * Method that emulates Excel PMT(interest_rate, number_payments, PV) function, which calculates the payments for a loan.
     * <p>
     * Formula: PMT = (pv * t * (1+t)^n) / ((1+t)^n -1)
     * <p>
     * where: - t is the interest rate ((r / 100) / 12)
     * <p>
     * example: if you borrowed £15000 over 24 months at 6.7% APR, it would work out as:
     * <p>
     * PMT = (15000 * ((6.7/100)/12) * (1+((6.7/100)/12))^24) / ((1+((6.7/100)/12))^24 -1)
     * <ul>
     * <li>part1 = (15000 * ((6.7/100)/12) </li>
     * <li>part2 = (1+((6.7/100)/12))^24) </li>
     * <li>part3 = ((1+((6.7/100)/12))^24 -1) </li>
     * </ul>
     *
     * @param r - periodic interest rate represented as a decimal.
     * @param nper - number of total payments or periods.
     * @param pv - present value -- borrowed principal.
     * @return <code>double</code> representing periodic payment amount.
     * @see <a href="http://www.whatsthecost.com/faq.aspx">http://www.whatsthecost.com/faq.aspx</a>
     */
    public static double pmt(final double r, final int nper, final double pv)
    {
        final double interestRate = (r / 100) / 12;
        final double part1 = interestRate * pv;
        final double part2 = Math.pow(interestRate + 1, nper);
        final double part3 = part2 - 1;

        return part1 * part2 / part3;

    }

    public static Optional<String> getFileExtension(final String filename)
    {
        return Optional.ofNullable(filename)
            .filter(f -> f.contains("."))
            .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }

    public static boolean checkIfFileExists(final String filePath)
    {
        return Files.exists(Paths.get(filePath));
    }
}
