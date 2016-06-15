package edu.jhu.icm.wvtools;

import edu.jhu.icm.wvtools.io.InfoReader;
import edu.jhu.icm.wvtools.io.InfoReaderException;
import edu.jhu.icm.wvtools.io.WvReader;
import edu.jhu.icm.wvtools.util.DataDeinterlacer;
import edu.jhu.icm.wvtools.util.DataScaler;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Facade that dispatches calls from driver.
 * Created by ran on 6/14/16.
 */
public class WvToolsFacade {

    /**
     * Outputs the raw unscaled output.
     * @param inputPrefix Prefix of input file.
     * @param noHeaders Flag to not print headers.
     */
    public void generateRawOutput(String inputPrefix, boolean noHeaders) {
        try {
            InfoReader infoReader = new InfoReader(inputPrefix);

            if (!noHeaders) {
                for (String s : infoReader.getChannelNames()) {
                    System.out.printf("%s\t", s);
                }
                System.out.println();
            }

            WvReader wvReader = new WvReader(new File(inputPrefix + ".wv"));
            List<Short> shorts = wvReader.allShorts();

            int currentCounter = 0;
            for (Short s : shorts) {
                System.out.printf("%d\t", s);
                if (currentCounter % infoReader.getNumChannels() == infoReader.getNumChannels() - 1) System.out.println();
            }

        } catch (IOException | InfoReaderException e) {
            System.err.println(e.getMessage());
        }
    }

    public void generatePhysioNetOutput(String inputPrefix) {

    }

    @SuppressWarnings("unchecked")
    public void generateScaledOutput(String inputPrefix, boolean noHeaders) {

        try {
            InfoReader infoReader = new InfoReader(inputPrefix);

            if (!noHeaders) {
                for (int c = 0; c < infoReader.getChannelNames().size(); c++) {
                    System.out.printf("%s(%s)\t", infoReader.getChannelNames().get(c), infoReader.getUnits().get(c));
                }
                System.out.println();
            }

            WvReader wvReader = new WvReader(new File(inputPrefix + ".wv"));
            List<Short> shorts = wvReader.allShorts();

            DataDeinterlacer ddi = new DataDeinterlacer();
            DataScaler ds = new DataScaler();

            List<Short>[] deinterlacedData = ddi.deinterlaceShorts(shorts, infoReader.getNumChannels());
            List<Double>[] scaledDeinterlacedData = new List[infoReader.getNumChannels()];
            for (int c = 0; c < infoReader.getNumChannels(); c++) {
                scaledDeinterlacedData[c] = ds.scaleShorts(deinterlacedData[c], infoReader.getGains().get(c));
            }

            for (int c = 0; c < shorts.size(); c++) {
                System.out.printf("%f\t", scaledDeinterlacedData[c % infoReader.getNumChannels()].get(c / infoReader.getNumChannels()));
                if (c % infoReader.getNumChannels() == infoReader.getNumChannels() - 1) System.out.println();
            }

        } catch (IOException | InfoReaderException e) {
            System.err.println(e.getMessage());
        }

    }
}
