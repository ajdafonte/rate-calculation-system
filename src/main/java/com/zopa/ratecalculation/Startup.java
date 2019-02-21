package com.zopa.ratecalculation;

import java.util.Currency;
import java.util.Locale;

import com.zopa.ratecalculation.input.InputArgHandler;
import com.zopa.ratecalculation.loan.ILoanCalculator;
import com.zopa.ratecalculation.loan.LenderRepository;
import com.zopa.ratecalculation.loan.LoanCalculator;
import com.zopa.ratecalculation.reader.CsvDataReader;


/**
 * Startup class.
 * <p>
 * Remarks: Here is also defined a set of configuration parameters.
 */
public class Startup
{
    // Configuration parameters
    private static final String FILE_DELIMITER = ",";
    private static final String VALID_INPUT_FILE_EXTENSION = "csv";
    private static final Integer MIN_LOAN_AMOUNT = 1000;
    private static final Integer MAX_LOAN_AMOUNT = 15000;
    private static final Integer INCREMENT_SIZE_LOAN_AMOUNT = 100;
    private static final Integer LOAN_DURATION = 36;
    private static final String CURRENCY_SYMBOL = Currency.getInstance(Locale.UK).getSymbol();

    public static void main(final String[] args)
    {
        final InputArgHandler inputArgHandler =
            new InputArgHandler(VALID_INPUT_FILE_EXTENSION, MIN_LOAN_AMOUNT, MAX_LOAN_AMOUNT, INCREMENT_SIZE_LOAN_AMOUNT);
        final ILoanCalculator loanCalculator = new LoanCalculator(new LenderRepository(new CsvDataReader(FILE_DELIMITER)), LOAN_DURATION);

        final RateCalculator rateCalculator = new RateCalculator(inputArgHandler, loanCalculator, CURRENCY_SYMBOL);
        rateCalculator.start(args);
    }
}
