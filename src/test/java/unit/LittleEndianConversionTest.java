package unit;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for little endian conversion.
 * Created by ran on 6/14/16.
 */
public class LittleEndianConversionTest {

    @Test
    public void testPositiveConversion() {
        int lower = 1;
        int upper = 1;

        short result = (short)(((upper & 0xff) << 8) | (lower & 0xff));
        assertEquals(257, result);
    }

    @Test
    public void testNegativeConversion() {
        int lower = 0xfb;
        int upper = 0xff;
        short result = (short)(((upper & 0xff) << 8) | (lower & 0xff));
        assertEquals(-5, result);
    }
}
