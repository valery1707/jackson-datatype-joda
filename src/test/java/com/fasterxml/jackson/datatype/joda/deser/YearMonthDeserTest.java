package com.fasterxml.jackson.datatype.joda.deser;

import java.io.IOException;
import java.util.TimeZone;

import org.joda.time.YearMonth;
import org.joda.time.chrono.ISOChronology;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaTestBase;

public class YearMonthDeserTest extends JodaTestBase
{
    /*
    /**********************************************************
    /* Test methods
    /**********************************************************
     */

    private final ObjectMapper MAPPER = jodaMapper();

    public void testDeserYearMonth() throws Exception
    {
        String yearMonthString = new YearMonth(2013, 8).toString();
        YearMonth yearMonth = MAPPER.readValue(quote(yearMonthString), YearMonth.class);
        assertEquals(new YearMonth(2013, 8), yearMonth);
    }

    public void testDeserYearMonthWithTimeZone() throws Exception
    {
        MAPPER.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
        
        String yearMonthString = new YearMonth(2013, 8).toString();
        YearMonth yearMonth = MAPPER.readValue(quote(yearMonthString), YearMonth.class);
        assertEquals(new YearMonth(2013, 8), yearMonth);
        assertEquals(ISOChronology.getInstanceUTC(), yearMonth.getChronology());
    }

    public void testDeserYearMonthFromEmptyString() throws Exception
    {
        YearMonth yearMonth = MAPPER.readValue(quote(""), YearMonth.class);
        assertNull(yearMonth);
    }

    public void testDeserYearMonthFailsForUnexpectedType() throws IOException
    {
        try
        {
            MAPPER.readValue("{\"year\":2013}", YearMonth.class);
            fail();
        } catch (JsonMappingException e) {
            verifyException(e, "expected JSON String");
        }
    }

}
