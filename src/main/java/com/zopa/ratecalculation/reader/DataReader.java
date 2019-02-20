package com.zopa.ratecalculation.reader;

import java.io.IOException;
import java.util.List;


/**
 *
 */
public interface DataReader
{
    /**
     * @param filePath
     * @param skipHeader
     * @return
     * @throws IOException
     */
    List<List<String>> readDataFile(final String filePath, boolean skipHeader) throws IOException;
}
