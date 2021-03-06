package functional;

import edu.jhu.icm.wvtools.WvToolsFacade;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Functional test for the tools facade.
 * Created by ran on 6/14/16.
 */
public class WvToolsFacadeTest {

    private static WvToolsFacade facade;

    @BeforeClass
    public static void initialize() {
        facade = new WvToolsFacade();
    }

    @Test
    public void testRawOutput() {
        facade.generateRawOutput("testData/test", false);
    }

    @Test
    public void testScaledOutput() {
        facade.generateScaledOutput("testData/test", false);
    }
}
