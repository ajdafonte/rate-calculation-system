package com.zopa.ratecalculation.util;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;


/**
 *
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

    static public double pmt(final double r, final int nper, final double pv)
    {
//        =(1000 * ((7/100)/12) * (1+((7/100)/12))^36) / ((1+((7/100)/12))^36 -1)
        // val1 =  (1000 * ((7/100)/12)
        // val2 =  (1+((7/100)/12))^36)
        // val3 = ((1+((7/100)/12))^36 -1)
        final double rateCalc = (r / 100) / 12;
        final double val1 = rateCalc * pv;
        final double val2 = Math.pow(rateCalc + 1, nper);
        final double val3 = val2 - 1;

        return val1 * val2 / val3;

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
