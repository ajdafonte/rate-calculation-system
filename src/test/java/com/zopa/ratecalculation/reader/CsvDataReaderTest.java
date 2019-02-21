package com.zopa.ratecalculation.reader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;


/**
 * CsvDataReaderTest class - Test CsvDataReader class.
 */
public class CsvDataReaderTest
{
    private static final List<List<String>> MOCK_DATA_WITH_HEADER;
    private static final List<List<String>> MOCK_DATA;
    private static final List<List<String>> MOCK_DATA_ONLY_HEADER;
    private static final List<List<String>> MOCK_INCOMPLETE_DATA;
    private static final List<String> MOCK_RECORD1;
    private static final List<String> MOCK_RECORD2;
    private static final List<String> MOCK_RECORD3;
    private static final List<String> MOCK_RECORD_WITH_EMPTY_VALUE1;
    private static final List<String> MOCK_RECORD_WITH_EMPTY_VALUE2;
    private static final List<String> MOCK_RECORD_WITH_EMPTY_VALUE3;
    private static final List<String> MOCK_RECORD_HEADER;
    private static final String MOCK_FILEPATH_DATA = "src/test/resources/test.csv";
    private static final String MOCK_FILEPATH_NO_DATA = "src/test/resources/testEmpty.csv";
    private static final String MOCK_FILEPATH_UNKNOWN_FILE = "src/test/resources/unknown.csv";
    private static final String MOCK_FILEPATH_DATA_ONLY_HEADER = "src/test/resources/testOnlyHeader.csv";
    private static final String MOCK_FILEPATH_INCOMPLETE_DATA = "src/test/resources/testIncomplete.csv";
    private static final String MOCK_DELIMITER_COMMA = ",";
    private static final String MOCK_NAME1 = "Bob";
    private static final String MOCK_NAME2 = "Alice";
    private static final String MOCK_NAME3 = "Cesar";
    private static final String MOCK_NAME_HEADER = "Lender";
    private static final String MOCK_RATE_HEADER = "Rate";
    private static final String MOCK_AMOUNT_HEADER = "Available";
    private static final String MOCK_RATE1 = "0.05";
    private static final String MOCK_RATE2 = "0.08";
    private static final String MOCK_RATE3 = "0.07";
    private static final String MOCK_AMOUNT1 = "900";
    private static final String MOCK_AMOUNT2 = "750";
    private static final String MOCK_AMOUNT3 = "400";
    private static final String MOCK_EMPTY_VALUE = "";

    static
    {
        MOCK_RECORD_HEADER = Arrays.asList(MOCK_NAME_HEADER, MOCK_RATE_HEADER, MOCK_AMOUNT_HEADER);
        MOCK_RECORD1 = Arrays.asList(MOCK_NAME1, MOCK_RATE1, MOCK_AMOUNT1);
        MOCK_RECORD2 = Arrays.asList(MOCK_NAME2, MOCK_RATE2, MOCK_AMOUNT2);
        MOCK_RECORD3 = Arrays.asList(MOCK_NAME3, MOCK_RATE3, MOCK_AMOUNT3);
        MOCK_RECORD_WITH_EMPTY_VALUE1 = Arrays.asList(MOCK_EMPTY_VALUE, MOCK_RATE1, MOCK_AMOUNT1);
        MOCK_RECORD_WITH_EMPTY_VALUE2 = Arrays.asList(MOCK_NAME2, MOCK_EMPTY_VALUE, MOCK_AMOUNT2);
        MOCK_RECORD_WITH_EMPTY_VALUE3 = Arrays.asList(MOCK_NAME3, MOCK_RATE3, MOCK_EMPTY_VALUE);
        MOCK_DATA_WITH_HEADER = Arrays.asList(MOCK_RECORD_HEADER, MOCK_RECORD1, MOCK_RECORD2, MOCK_RECORD3);
        MOCK_DATA = Arrays.asList(MOCK_RECORD1, MOCK_RECORD2, MOCK_RECORD3);
        MOCK_INCOMPLETE_DATA = Arrays.asList(MOCK_RECORD_WITH_EMPTY_VALUE1, MOCK_RECORD_WITH_EMPTY_VALUE2, MOCK_RECORD_WITH_EMPTY_VALUE3);
        MOCK_DATA_ONLY_HEADER = Collections.singletonList(MOCK_RECORD_HEADER);
    }

    @Test
    public void givenFileWithData_whenReadData_thenReturnListOfData() throws IOException
    {
        // given
        final CsvDataReader reader = new CsvDataReader(MOCK_DELIMITER_COMMA);
        final List<List<String>> expectedData = MOCK_DATA;

        // when
        final List<List<String>> data = reader.readDataFile(MOCK_FILEPATH_DATA, true);

        // then
        assertFalse(data.isEmpty());
        assertEquals(expectedData.size(), data.size());
        assertEquals(expectedData, data);
    }

    // test with no data
    @Test
    public void givenFileWithoutData_whenReadData_thenReturnEmptyListOfData() throws IOException
    {
        // given
        final CsvDataReader reader = new CsvDataReader(MOCK_DELIMITER_COMMA);

        // when
        final List<List<String>> data = reader.readDataFile(MOCK_FILEPATH_NO_DATA, true);

        // then
        assertTrue(data.isEmpty());
    }

    // test with no file exist
    @Test
    public void givenUnknownFile_whenReadData_thenThrowIOException()
    {
        // given
        final CsvDataReader reader = new CsvDataReader(MOCK_DELIMITER_COMMA);

        // when + then
        assertThrows(IOException.class, () -> reader.readDataFile(MOCK_FILEPATH_UNKNOWN_FILE, true));
    }

    // test with only header data
    @Test
    public void givenFileOnlyWithHeader_whenReadData_thenReturnEmptyListOfData() throws IOException
    {
        // given
        final CsvDataReader reader = new CsvDataReader(MOCK_DELIMITER_COMMA);

        // when
        final List<List<String>> data = reader.readDataFile(MOCK_FILEPATH_DATA_ONLY_HEADER, true);

        // then
        assertTrue(data.isEmpty());
    }

    @Test
    public void givenFileOnlyWithHeader_whenReadDataWithHeader_thenReturnListOfDataOnlyWithHeader() throws IOException
    {
        // given
        final CsvDataReader reader = new CsvDataReader(MOCK_DELIMITER_COMMA);

        final List<List<String>> expectedData = MOCK_DATA_ONLY_HEADER;

        // when
        final List<List<String>> data = reader.readDataFile(MOCK_FILEPATH_DATA_ONLY_HEADER, false);

        // then
        assertFalse(data.isEmpty());
        assertEquals(expectedData.size(), data.size());
        assertEquals(expectedData, data);
    }

    @Test
    public void givenFileWithData_whenReadDataWithHeader_thenReturnListOfDataWithHeader() throws IOException
    {
        // given
        final CsvDataReader reader = new CsvDataReader(MOCK_DELIMITER_COMMA);

        final List<List<String>> expectedData = MOCK_DATA_WITH_HEADER;

        // when
        final List<List<String>> data = reader.readDataFile(MOCK_FILEPATH_DATA, false);

        // then
        assertFalse(data.isEmpty());
        assertEquals(expectedData.size(), data.size());
        assertEquals(expectedData, data);
    }

    // test with empty values in some fields
    @Test
    public void givenFileWithIncompleteData_whenReadData_thenReturnListOfIncompleteData() throws IOException
    {
        // given
        final CsvDataReader reader = new CsvDataReader(MOCK_DELIMITER_COMMA);
        final List<List<String>> expectedData = MOCK_INCOMPLETE_DATA;

        // when
        final List<List<String>> data = reader.readDataFile(MOCK_FILEPATH_INCOMPLETE_DATA, true);

        // then
        assertFalse(data.isEmpty());
        assertEquals(expectedData.size(), data.size());
        assertEquals(expectedData, data);
    }
}
