package com.zopa.ratecalculation.input;

import com.zopa.ratecalculation.error.InputArgException;
import com.zopa.ratecalculation.util.Util;


/**
 *
 */
public class InputArgHandler
{
    private static final String NEW_LINE = System.lineSeparator();

    private final String fileExtension;
    private final int minLoanAmount;
    private final int maxLoanAmount;
    private final int incrementSizeLoanAmount;

    public InputArgHandler(final String fileExtension, final int minLoanAmount, final int maxLoanAmount, final int incrementSizeLoanAmount)
    {
        this.fileExtension = fileExtension;
        this.minLoanAmount = minLoanAmount;
        this.maxLoanAmount = maxLoanAmount;
        this.incrementSizeLoanAmount = incrementSizeLoanAmount;
    }

    public void validateInputArgs(final String[] args) throws InputArgException
    {
        if (!validateNumberOfInputArgs(args))
        {
            throw new InputArgException("Invalid number of input args");
        }

        final String filePath = args[0];
        final String loanAmount = args[1];

        if (!validateFile(filePath, fileExtension) || !validateLoanAmount(loanAmount))
        {
            throw new InputArgException("Invalid definition of input args");
        }
    }

    public String getFilePath(final String[] args)
    {
        return validateNumberOfInputArgs(args) ? args[InputArgType.FILEPATH.getPosition()] : "";
    }

    public String getLoanAmount(final String[] args)
    {
        return validateNumberOfInputArgs(args) ? args[InputArgType.LOAN_AMOUNT.getPosition()] : "";
    }

    public String generateStartupHelpMessage()
    {
        return ">> Invalid arguments to start the Rate Calculation System."
            + NEW_LINE
            + ">> Arguments allowed: <filePath> <loan amount>"
            + NEW_LINE
            + ">> Example to start Rate Calculation System: RateCalculatorStartup <filePath> <loan amount>"
            + NEW_LINE
            + ">> Constraints:"
            + NEW_LINE
            + ">> - <filePath> : File should exist in the system and with extension: "
            + fileExtension
            + NEW_LINE
            + ">> - <loanAmount> : Should be a number that belong to the following range ["
            + minLoanAmount + ";" + maxLoanAmount
            + "] and should be a multiple of " + incrementSizeLoanAmount
            + NEW_LINE;
    }

    private boolean validateNumberOfInputArgs(final String[] args)
    {
        return args.length == InputArgType.getNumberOfArgs();
    }

    private boolean validateLoanAmount(final String amount)
    {
        return validateIfNumber(amount) && validateLoanAmountRestrictions(amount);
    }

    private boolean validateLoanAmountRestrictions(final String amount)
    {
        final int num = Integer.parseInt(amount);
        return (num >= minLoanAmount && num <= maxLoanAmount) &&
            (num % incrementSizeLoanAmount == 0);
    }

    private boolean validateIfNumber(final String amount)
    {
        try
        {
            Integer.parseInt(amount);
        }
        catch (final NumberFormatException e)
        {
            return false;
        }
        return true;
    }

    private boolean validateFile(final String filePath, final String fileExtension)
    {
        return Util.checkIfFileExists(filePath) && checkFileExtension(filePath, fileExtension);
    }

    private boolean checkFileExtension(final String filePath, final String fileExtension)
    {
        final String fileExtensionInPath = Util.getFileExtension(filePath).orElse("");
        return fileExtensionInPath.equalsIgnoreCase(fileExtension);
    }

}
