package unit.edu.jhu.icm.wvtools.io;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Info reader regex test.
 * Created by ran on 6/14/16.
 */
public class InfoRegexTest {

    @Test
    public void testMatch() {
        String testLine = "2224BN1416911131-20141224-184234.wv 125 1 mV \"ECG\" gain=0.005 interleave 4 0 1";
        Pattern pattern = Pattern.compile("([0-9a-zA-Z\\-.]+) (\\d+) (\\d+) ([0-9a-zA-Z]+) \"([0-9a-zA-Z]+)\" gain=(\\d.\\d+)");
        Matcher matcher = pattern.matcher(testLine);
        assertTrue(matcher.find());

        assertEquals("2224BN1416911131-20141224-184234.wv", matcher.group(1));
        assertEquals("125", matcher.group(2));
        assertEquals("1", matcher.group(3));
        assertEquals("mV", matcher.group(4));
        assertEquals("ECG", matcher.group(5));
        assertEquals("0.005", matcher.group(6));

    }

}
