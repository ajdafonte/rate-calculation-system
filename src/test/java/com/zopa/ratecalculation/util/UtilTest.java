package com.zopa.ratecalculation.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;


/**
 *
 */
public class UtilTest
{
    // test to double values ok
    @Test
    public void givenEligibleDoubleStringValues_whenConvertToDouble_thenReturnDoubleValues()
    {
        // given
        final String[] testCases = {
            "1", "1.0", "0.1", "0.0001", "11.12345678"
        };
        final double[] expectedValues = {
            1, 1.0, 0.1, 0.0001, 11.12345678
        };

        // when
        final double[] convertedValues = new double[testCases.length];
        for (int i = 0; i < testCases.length; i++)
        {
            convertedValues[i] = Util.toDouble(testCases[i]);
        }

        // then
        for (int i = 0; i < expectedValues.length; i++)
        {
            assertEquals(expectedValues[i], convertedValues[i]);
        }
    }

    // test to double values nok
    @Test
    public void givenIneligibleDoubleStringValues_whenConvertToDouble_thenReturnDefaultDoubleValue()
    {
        // given
        final String[] testCases = {
            "lol", "", ",,"
        };
        final double expectedValue = 0;

        // when
        final double[] convertedValues = new double[testCases.length];
        for (int i = 0; i < testCases.length; i++)
        {
            convertedValues[i] = Util.toDouble(testCases[i]);
        }

        // then
        assertTrue(Arrays.stream(convertedValues)
            .allMatch(value -> value == expectedValue));
    }

    // test file exist
    @Test
    public void givenExistentFile_whenCheckIfFileExists_returnValid()
    {
        // given
        final String filePath = "src/test/resources/test.csv";

        // when + then
        assertTrue(Util.checkIfFileExists(filePath));
    }

    // test file does not exist
    @Test
    public void givenUnknownFile_whenValidatingFileInput_returnInvalid()
    {
        // given
        final String filePath = "src/test/resources/unknown.csv";

        // when + then
        assertFalse(Util.checkIfFileExists(filePath));
    }

    // test pmt
    @Test
    public void givenValidValues_whenCalculatePmt_thenReturnCorrectValues()
    {
        // given
        final double[] rates = {
            7
        };
        final int[] numPayments = {
            36
        };
        final double[] amounts = {
            1000
        };
        final double[] expectedValues = {
            30.877096865371833
        };

        // when
        final int numValidTestCases = Math.min(rates.length, Math.min(numPayments.length, amounts.length));
        final double[] pmtValues = new double[numValidTestCases];
        for (int i = 0; i < numValidTestCases; i++)
        {
            pmtValues[i] = Util.pmt(rates[i], numPayments[i], amounts[i]);
        }

        // then
        for (int i = 0; i < pmtValues.length; i++)
        {
            assertEquals(expectedValues[i], pmtValues[i]);
        }
    }

    // test file extension
    @Test
    public void givenFileNameValues_whenGetFileExtension_thenReturnExpectedValues()
    {
        // given
        final String[] testCases = {
            "test.csv", "test.txt", ".gitignore", "test.tar.gz", "test"
        };
        final Optional[] expectedValues = new Optional[] {
            Optional.of("csv"), Optional.of("txt"), Optional.of("gitignore"), Optional.of("gz"), Optional.empty()
        };

        // when
        final Optional[] convertedValues = new Optional[testCases.length];
        for (int i = 0; i < testCases.length; i++)
        {
            convertedValues[i] = Util.getFileExtension(testCases[i]);
        }

        // then
        for (int i = 0; i < expectedValues.length; i++)
        {
            assertEquals(expectedValues[i], convertedValues[i]);
        }
    }

    // test to percentage value
    @Test
    public void givenNumbersValuesInDecimals_whenConvertToPercentageValue_thenReturnExpectedPercentageValues()
    {
        // given
        final double[] testCases = {
            0.01, 0.1, 0.0056, 0.075, 0.00
        };
        final double[] expectedValues = {
            1, 10, 0.5599999999999999, 7.5, 0.00
        };

        // when
        final double[] convertedValues = new double[testCases.length];
        for (int i = 0; i < testCases.length; i++)
        {
            convertedValues[i] = Util.toPercentageValue(testCases[i]);
        }

        // then
        for (int i = 0; i < expectedValues.length; i++)
        {
            assertEquals(expectedValues[i], convertedValues[i]);
        }
    }
}
