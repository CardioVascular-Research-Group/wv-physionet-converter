package unit.edu.jhu.icm.wvtools.io;

import edu.jhu.icm.wvtools.io.InfoReader;
import edu.jhu.icm.wvtools.io.InfoReaderException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Unit test for InfoReader
 * Created by ran on 6/14/16.
 */
public class InfoReaderTest {

    private static InfoReader reader;

    @BeforeClass
    public static void initialize() {

        try {
            reader = new InfoReader("testData/test");
        } catch (FileNotFoundException | InfoReaderException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void testReadDate() {
        DateFormat formatter = new SimpleDateFormat("dd MMM yyyy kk:mm:ss z");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
        assertEquals("24 Dec 2014 18:42:34 GMT", formatter.format(reader.getStartDate()));
    }

    @Test
    public void testSampleRate() {
        assertTrue(125 == reader.getSampleRate());
    }

    @Test
    public void testReadUnits() {
        // Literally unit testing huehuehue
        assertEquals(4, reader.getUnits().size());
        assertEquals("mV", reader.getUnits().get(0));
        assertEquals("units", reader.getUnits().get(1));
        assertEquals("mmHg", reader.getUnits().get(2));
        assertEquals("mmHg", reader.getUnits().get(3));
    }

    @Test
    public void testReadChannelNames() {
        assertEquals(4, reader.getChannelNames().size());
        assertEquals("ECG", reader.getChannelNames().get(0));
        assertEquals("PLETH", reader.getChannelNames().get(1));
        assertEquals("ABP", reader.getChannelNames().get(2));
        assertEquals("CO2", reader.getChannelNames().get(3));
    }

    @Test
    public void testGains() {
        assertEquals(4, reader.getGains().size());
        assertTrue(.005 == reader.getGains().get(0));
        assertTrue(.00390625 == reader.getGains().get(1));
        assertTrue(.00390625 == reader.getGains().get(2));
        assertTrue(.00390625 == reader.getGains().get(3));
    }


}
