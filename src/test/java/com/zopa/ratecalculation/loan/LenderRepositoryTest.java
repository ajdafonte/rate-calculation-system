package com.zopa.ratecalculation.loan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.zopa.ratecalculation.loan.domain.Lender;
import com.zopa.ratecalculation.loan.repo.LenderRepository;
import com.zopa.ratecalculation.reader.DataReader;


/**
 *
 */
@ExtendWith(MockitoExtension.class)
public class LenderRepositoryTest
{
    @Mock
    private DataReader reader;

    private LenderRepository lenderRepository;

    private static final String MOCK_FILEPATH = "/src/test/java/resources/dummy.csv";
    private static final List<List<String>> MOCK_DATA;
    private static final List<String> MOCK_RECORD1;
    private static final List<String> MOCK_RECORD2;
    private static final List<String> MOCK_RECORD3;
    private static final String MOCK_NAME1 = "Bob";
    private static final String MOCK_NAME2 = "Alice";
    private static final String MOCK_NAME3 = "Cesar";
    private static final String MOCK_RATE1 = "0.05";
    private static final String MOCK_RATE2 = "0.08";
    private static final String MOCK_RATE3 = "0.07";
    private static final String MOCK_AMOUNT1 = "900";
    private static final String MOCK_AMOUNT2 = "750";
    private static final String MOCK_AMOUNT3 = "400";
    private static final Lender MOCK_LENDER1;
    private static final Lender MOCK_LENDER2;
    private static final Lender MOCK_LENDER3;
    private static final List<Lender> MOCK_LENDERS;

    static
    {
        MOCK_RECORD1 = Arrays.asList(MOCK_NAME1, MOCK_RATE1, MOCK_AMOUNT1);
        MOCK_RECORD2 = Arrays.asList(MOCK_NAME2, MOCK_RATE2, MOCK_AMOUNT2);
        MOCK_RECORD3 = Arrays.asList(MOCK_NAME3, MOCK_RATE3, MOCK_AMOUNT3);
        MOCK_DATA = Arrays.asList(MOCK_RECORD1, MOCK_RECORD2, MOCK_RECORD3);
        MOCK_LENDER1 = generateLender(MOCK_NAME1, Double.parseDouble(MOCK_RATE1), Double.parseDouble(MOCK_AMOUNT1));
        MOCK_LENDER2 = generateLender(MOCK_NAME2, Double.parseDouble(MOCK_RATE2), Double.parseDouble(MOCK_AMOUNT2));
        MOCK_LENDER3 = generateLender(MOCK_NAME3, Double.parseDouble(MOCK_RATE3), Double.parseDouble(MOCK_AMOUNT3));
        MOCK_LENDERS = Arrays.asList(MOCK_LENDER1, MOCK_LENDER2, MOCK_LENDER3);
    }

    private static Lender generateLender(final String name, final double rate, final double availableAmount)
    {
        return new Lender.LenderBuilder()
            .withName(name)
            .withRate(rate)
            .withAvailableAmount(availableAmount)
            .build();

    }

    @BeforeEach
    public void setUp()
    {
        this.lenderRepository = new LenderRepository(reader);
    }

    @Test
    public void givenCorrectData_whenFindAllLenders_returnAllLenders() throws IOException
    {
        // given
        final List<Lender> expectedLenders = MOCK_LENDERS;
        Mockito.when(reader.readDataFile(anyString(), anyBoolean())).thenReturn(MOCK_DATA);

        // when
        final List<Lender> lenders = lenderRepository.findAll(MOCK_FILEPATH);

        // then
        assertFalse(lenders.isEmpty());
        assertEquals(expectedLenders.size(), lenders.size());
        assertEquals(expectedLenders, lenders);
    }

    @Test
    public void givenExceptionReadingData_whenFindAllLenders_returnIOException() throws IOException
    {
        // given
        Mockito.when(reader.readDataFile(anyString(), anyBoolean())).thenThrow(IOException.class);

        // when + then
        Assertions.assertThrows(IOException.class, () -> lenderRepository.findAll(MOCK_FILEPATH));
    }

    @Test
    public void givenEmptyData_whenFindAllLenders_returnEmptyLenders() throws IOException
    {
        // given
        final List<Lender> expectedLenders = Collections.emptyList();
        Mockito.when(reader.readDataFile(anyString(), anyBoolean())).thenReturn(Collections.emptyList());

        // when
        final List<Lender> lenders = lenderRepository.findAll(MOCK_FILEPATH);

        // then
        assertTrue(lenders.isEmpty());
        assertEquals(expectedLenders.size(), lenders.size());
        assertEquals(expectedLenders, lenders);
    }

}
