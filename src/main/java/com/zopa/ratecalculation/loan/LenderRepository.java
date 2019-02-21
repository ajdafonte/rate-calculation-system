package com.zopa.ratecalculation.loan;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.zopa.ratecalculation.reader.DataReader;
import com.zopa.ratecalculation.util.Util;


/**
 * LenderRepository class - Contains methods to read lender information from a certain file.
 */
public class LenderRepository
{
    private final DataReader reader;

    public LenderRepository(final DataReader reader)
    {
        this.reader = reader;
    }

    List<Lender> findAll(final String filePath) throws IOException
    {
        return LenderMapper.mapFromReader(reader.readDataFile(filePath, true));
    }

    private static class LenderMapper
    {
        private static final int LENDER_NAME_POSITION_IN_FILE = 0;
        private static final int LENDER_RATE_POSITION_IN_FILE = 1;
        private static final int LENDER_AMOUNT_POSITION_IN_FILE = 2;

        static List<Lender> mapFromReader(final List<List<String>> data)
        {
            return data.stream()
                .map(LenderMapper::mapFromReaderRecord)
                .collect(Collectors.toList());
        }

        private static Lender mapFromReaderRecord(final List<String> record)
        {
            Lender lenderData = null;
            if (!record.isEmpty())
            {
                lenderData = new Lender.LenderBuilder()
                    .withName(record.get(LENDER_NAME_POSITION_IN_FILE))
                    .withRate(Util.toDouble(record.get(LENDER_RATE_POSITION_IN_FILE)))
                    .withAvailableAmount(Util.toDouble(record.get(LENDER_AMOUNT_POSITION_IN_FILE)))
                    .build();
            }

            return lenderData;
        }
    }
}
