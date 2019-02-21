package com.zopa.ratecalculation.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * CsvDataReader class - Contains all methods to know how to read a csv file.
 */
public class CsvDataReader implements DataReader
{
    private final String csvDelimiter;

    public CsvDataReader(final String fileDelimiter)
    {
        this.csvDelimiter = fileDelimiter;
    }

    @Override
    public List<List<String>> readDataFile(final String filePath, final boolean skipHeader) throws IOException
    {
        final List<List<String>> records = new ArrayList<>();
        try (final BufferedReader br = new BufferedReader(new FileReader(filePath)))
        {
            if (skipHeader)
            {
                br.readLine();
            }
            String line;
            while ((line = br.readLine()) != null)
            {
                final String[] values = line.split(csvDelimiter, -1);
                records.add(Arrays.asList(values));
            }
        }

        return records;
    }
}
