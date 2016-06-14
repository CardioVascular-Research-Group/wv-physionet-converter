package edu.jhu.icm.wvtools;

import edu.jhu.icm.wvtools.io.InfoReader;
import edu.jhu.icm.wvtools.io.InfoReaderException;
import edu.jhu.icm.wvtools.io.WvReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Facade that dispatches calls from driver.
 * Created by ran on 6/14/16.
 */
public class WvToolsFacade {

    public void generateRawOutput(String inputPrefix, boolean noHeaders) {
        try {
            InfoReader infoReader = new InfoReader(inputPrefix);

            if (!noHeaders) {
                for (String s : infoReader.getChannelNames()) {
                    System.out.printf("%s\t", s);
                }
                System.out.println();
            }

            int numChannels = infoReader.getNumChannels();
            WvReader wvReader = new WvReader(new File(inputPrefix + ".wv"));
            List<Short> shorts = wvReader.allShorts();

            int currentCounter = 0;
            for (Short s : shorts) {
                System.out.printf("%d\t", s);
                if (currentCounter % numChannels == numChannels - 1) {
                    System.out.println();
                }
            }

        } catch (IOException | InfoReaderException e) {
            System.err.println(e.getMessage());
        }
    }

    public void generatePhysioNetOutput(String inputPrefix) {

    }

    public void generateScaledOutput(String inputPrefix, boolean noHeaders) {

    }
}
