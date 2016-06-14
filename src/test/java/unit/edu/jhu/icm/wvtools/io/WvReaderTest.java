package unit.edu.jhu.icm.wvtools.io;

import edu.jhu.icm.wvtools.io.WvReader;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Unit tests for WvReader.
 * Created by ran on 6/14/16.
 */
public class WvReaderTest {

    @Test
    public void testRead() {

        try {
            WvReader reader = new WvReader(new File("testData/test.wv"));
            List<Short> shorts = reader.allShorts();
            assertTrue(1 == shorts.get(0));
            assertTrue(257 == shorts.get(1));

        } catch (IOException e) {
            fail();
        }

    }
}
