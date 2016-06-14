package unit.edu.jhu.icm.wvtools.utils;

import edu.jhu.icm.wvtools.util.ChecksumCalculator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Unit tests for checksum calculator.
 * Created by ran on 6/14/16.
 */
public class ChecksumCalculatorTest {

    private ChecksumCalculator calculator;

    @Before
    public void initialize() {
        calculator = new ChecksumCalculator(2);
    }

    @Test
    public void testAdditions() {
        calculator.addShort((short)32767, 0);
        assertTrue(32767 == calculator.getShort(0));
        calculator.addShort((short)1, 0);
        assertTrue(-32768 == calculator.getShort(0));
    }

    @Test
    public void testMultiChannel() {
        calculator.addInt(5, 0);
        calculator.addInt(2, 1);
        assertTrue(5 == calculator.getInt(0));
        assertTrue(2 == calculator.getInt(1));
    }

}
