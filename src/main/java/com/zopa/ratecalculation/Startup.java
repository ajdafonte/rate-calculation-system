package com.zopa.ratecalculation;

import java.util.Currency;
import java.util.Locale;

import com.zopa.ratecalculation.input.InputArgHandler;
import com.zopa.ratecalculation.loan.LoanCalculator;
import com.zopa.ratecalculation.loan.repo.LenderRepository;
import com.zopa.ratecalculation.reader.CsvDataReader;


public class Startup
{
    private static final String FILE_DELIMITER = ",";
    private static final String VALID_INPUT_FILE_EXTENSION = "csv";
    private static final Integer MIN_LOAN_AMOUNT = 1000;
    private static final Integer MAX_LOAN_AMOUNT = 15000;
    private static final Integer INCREMENT_SIZE_LOAN_AMOUNT = 100;
    private static final Integer LOAN_DURATION = 36;
    private static final String CURRENCY_SYMBOL = Currency.getInstance(Locale.UK).getSymbol();

    public static void main(final String[] args)
    {
        final RateCalculatorSystem rateCalculatorSystem = new RateCalculatorSystem(
            new InputArgHandler(VALID_INPUT_FILE_EXTENSION, MIN_LOAN_AMOUNT, MAX_LOAN_AMOUNT, INCREMENT_SIZE_LOAN_AMOUNT),
            new LoanCalculator(new LenderRepository(new CsvDataReader(FILE_DELIMITER)), LOAN_DURATION),
            CURRENCY_SYMBOL);

        rateCalculatorSystem.start(args);
    }
}
