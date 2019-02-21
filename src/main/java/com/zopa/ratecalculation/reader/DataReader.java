package com.zopa.ratecalculation.reader;

import java.io.IOException;
import java.util.List;


/**
 * DataReader interface - Describes all methods related when reading a file.
 */
public interface DataReader
{
    /**
     * Read data of a certain file.
     *
     * @param filePath - filename path
     * @param skipHeader - indicates if header should be read or not
     * @return <code>list</code> that contains all data of a file. Each line is wrapped inside a <code>list</code>
     * @throws IOException if occurs a problem when reading the file.
     */
    List<List<String>> readDataFile(final String filePath, boolean skipHeader) throws IOException;
}
