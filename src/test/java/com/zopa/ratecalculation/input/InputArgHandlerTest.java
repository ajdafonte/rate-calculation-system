package com.zopa.ratecalculation.input;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.zopa.ratecalculation.error.InputArgException;


/**
 *
 */
public class InputArgHandlerTest
{
    private static final String MOCK_FILE_EXTENSION = "txt";
    private static final int MOCK_MIN_LOAN_AMOUNT = 400;
    private static final int MOCK_MAX_LOAN_AMOUNT = 1000;
    private static final int MOCK_INCREMENT_SIZE_AMOUNT = 100;

    private InputArgHandler inputArgHandler;

    @BeforeEach
    public void setUp()
    {
        this.inputArgHandler = new InputArgHandler(MOCK_FILE_EXTENSION,
            MOCK_MIN_LOAN_AMOUNT, MOCK_MAX_LOAN_AMOUNT, MOCK_INCREMENT_SIZE_AMOUNT);
    }

    //test ok args
    @Test
    public void givenValidArgs_whenValidatingInputArgs_thenValidationOk()
    {
        // given
        final String[][] testCases = {
            new String[] {"src/test/resources/test.txt", "500"},
            new String[] {"src/test/resources/test.txt", "400"},
            new String[] {"src/test/resources/test.txt", "1000"},
        };

        // when + then
        for (final String[] mockArgs : testCases)
        {
            assertDoesNotThrow(() -> inputArgHandler.validateInputArgs(mockArgs));
        }
    }

    //test num input args invalid
    @Test
    public void givenNumArgsInvalid_whenValidatingInputArgs_thenThrowSpecificException()
    {
        // given
        final String[][] testCases = {
            new String[] {"1"},
            new String[] {"1", "2", "3"},
            new String[] {"1", "2", "3", "4"},
            new String[] {},
        };

        // when + then
        for (final String[] mockArgs : testCases)
        {
            assertThrows(InputArgException.class, () -> inputArgHandler.validateInputArgs(mockArgs));
        }
    }

    //test file does not exist
    @Test
    public void givenValidArgsWithUnknownFile_whenValidatingInputArgs_thenThrowSpecificException()
    {
        // given
        final String[] mockArgs = {"src/test/resources/unknown.txt", "500"};

        // when + then
        assertThrows(InputArgException.class, () -> inputArgHandler.validateInputArgs(mockArgs));
    }

    //test file with wrong extension
    @Test
    public void givenValidArgsWithFileBadExtension_whenValidatingInputArgs_thenThrowSpecificException()
    {
        // given
        final String[] mockArgs = {"src/test/resources/test.csv", "500"};

        // when + then
        assertThrows(InputArgException.class, () -> inputArgHandler.validateInputArgs(mockArgs));
    }

    //test amount not a number
    @Test
    public void givenValidArgsWithAmountNotNumber_whenValidatingInputArgs_thenThrowSpecificException()
    {
        // given
        final String[] mockArgs = {"src/test/resources/test.txt", "lol"};

        // when + then
        assertThrows(InputArgException.class, () -> inputArgHandler.validateInputArgs(mockArgs));
    }

    //test amount not inside range
    @Test
    public void givenValidArgsWithAmountNotInRange_whenValidatingInputArgs_thenThrowSpecificException()
    {
        // given
        final String[] mockArgs = {"src/test/resources/test.txt", "20000"};

        // when + then
        assertThrows(InputArgException.class, () -> inputArgHandler.validateInputArgs(mockArgs));
    }

    //test amount not multiple of 100
    @Test
    public void givenValidArgsWithAmountNotValidIncrementSize_whenValidatingInputArgs_thenThrowSpecificException()
    {
        // given
        final String[] mockArgs = {"src/test/resources/test.txt", "550"};

        // when + then
        assertThrows(InputArgException.class, () -> inputArgHandler.validateInputArgs(mockArgs));
    }

    //test getFilePath ok
    @Test
    public void givenValidArgsNumber_whenGetFilePathArg_thenReturnFilePathValue()
    {
        // given
        final String[] mockArgs = {"src/test/resources/test.txt", "500"};
        final String expectedFilePath = "src/test/resources/test.txt";

        // when
        final String filePath = inputArgHandler.getFilePath(mockArgs);

        // then
        assertFalse(filePath.isEmpty());
        assertEquals(expectedFilePath, filePath);
    }

    //test getFilePath nok
    @Test
    public void givenInvalidArgsNumber_whenGetFilePathArg_thenReturnEmptyValue()
    {
        // given
        final String[] mockArgs = {"src/test/resources/test.txt"};

        // when
        final String filePath = inputArgHandler.getFilePath(mockArgs);

        // then
        assertTrue(filePath.isEmpty());
    }

    //test getLoanAmount ok
    @Test
    public void givenValidArgsNumber_whenGetLoanAmountArg_thenReturnFilePathValue()
    {
        // given
        final String[] mockArgs = {"src/test/resources/test.txt", "500"};
        final String expectedLoanAmount = "500";

        // when
        final String loanAmount = inputArgHandler.getLoanAmount(mockArgs);

        // then
        assertFalse(loanAmount.isEmpty());
        assertEquals(expectedLoanAmount, loanAmount);
    }

    //test getLoanAmount nok
    @Test
    public void givenInvalidArgsNumber_whenGetLoanAmountArg_thenReturnEmptyValue()
    {
        // given
        final String[] mockArgs = {""};

        // when
        final String loanAmount = inputArgHandler.getLoanAmount(mockArgs);

        // then
        assertTrue(loanAmount.isEmpty());
    }

    //test startupMessage
    @Test
    public void whenGetStartupHelpMessage_thenReturnCorrectMessageValue()
    {
        // given
        final String newLine = System.lineSeparator();
        final String expectedMessage = ">> Invalid arguments to start the Rate Calculation System."
            + newLine
            + ">> Arguments allowed: <filePath> <loan amount>"
            + newLine
            + ">> Example to start Rate Calculation System: RateCalculatorStartup <filePath> <loan amount>"
            + newLine
            + ">> Constraints:"
            + newLine
            + ">> - <filePath> : File should exist in the system and with extension: "
            + MOCK_FILE_EXTENSION
            + newLine
            + ">> - <loanAmount> : Should be a number that belong to the following range ["
            + MOCK_MIN_LOAN_AMOUNT + ";" + MOCK_MAX_LOAN_AMOUNT
            + "] and should be a multiple of " + MOCK_INCREMENT_SIZE_AMOUNT
            + newLine;

        // when
        final String message = inputArgHandler.generateStartupHelpMessage();

        // then
        assertFalse(message.isEmpty());
        assertEquals(expectedMessage, message);
    }
}
