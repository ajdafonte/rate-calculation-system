package com.zopa.ratecalculation.loan.repo;

import java.io.IOException;
import java.util.List;

import com.zopa.ratecalculation.loan.domain.Lender;
import com.zopa.ratecalculation.loan.domain.LenderMapper;
import com.zopa.ratecalculation.reader.DataReader;


/**
 *
 */
public class LenderRepository
{
    private final DataReader reader;

    public LenderRepository(final DataReader reader)
    {
        this.reader = reader;
    }

    public List<Lender> findAll(final String filePath) throws IOException
    {
        return LenderMapper.mapFromReader(reader.readDataFile(filePath, true));
    }
}
