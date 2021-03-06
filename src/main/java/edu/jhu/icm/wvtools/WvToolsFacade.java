package edu.jhu.icm.wvtools;

import edu.jhu.icm.wvtools.io.*;
import edu.jhu.icm.wvtools.util.DataDeinterlacer;
import edu.jhu.icm.wvtools.util.DataScaler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Facade pattern; dispatches calls from driver.
 * Created by ran on 6/14/16.
 */
public class WvToolsFacade {

    private void generateOutput(String inputPrefix, boolean scale, boolean noHeaders) {
        try {
            InfoReader infoReader = new InfoReader(inputPrefix);
            WvReader wvReader = new WvReader(new File(inputPrefix + ".wv"));

            AmplitudeWriter writer = new AmplitudeWriter();
            DataScaler scaler = new DataScaler();

            DataDeinterlacer deinterlacer = new DataDeinterlacer();
            List<Short>[] data = deinterlacer.deinterlaceShorts(wvReader.allShorts(), infoReader.getNumChannels());

            if (scale) {
                // If scaled, labels consist of channel names w/ units in parentheses
                List<String> labels = new ArrayList<>();
                for (int c = 0; c < infoReader.getChannelNames().size(); c++) labels.add(infoReader.getChannelNames().get(c) + "(" + infoReader.getUnits().get(c) + ")");
                writer.write(System.out, scaler.scaleShorts(data, infoReader.getGains()), labels, infoReader.getSampleRate(), infoReader.getStartDate(), noHeaders);
            } else {
                writer.write(System.out, scaler.scaleShorts(data, 1), infoReader.getChannelNames(), infoReader.getSampleRate(), infoReader.getStartDate(), noHeaders);
            }

        } catch (IOException | InfoReaderException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Outputs the raw unscaled output.
     * @param inputPrefix Prefix of input file.
     * @param noHeaders Flag to not print data headers.
     */
    public void generateRawOutput(String inputPrefix, boolean noHeaders) {
        generateOutput(inputPrefix, false, noHeaders);
    }

    /**
     * Generates a physionet header.
     * @param inputPrefix Prefix of input file.
     */
    public void generatePhysioNetOutput(String inputPrefix) {
        try {
            InfoReader infoReader = new InfoReader(inputPrefix);
            WvReader wvReader = new WvReader(new File(inputPrefix + ".wv"));
            PhysioNetHeaderWriter physioNetHeaderWriter = new PhysioNetHeaderWriter(inputPrefix, infoReader, wvReader);
            physioNetHeaderWriter.write(System.out);

        } catch (IOException | InfoReaderException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Generates scaled output.
     * @param inputPrefix Prefix of input file.
     * @param noHeaders Flag to not print data headers.
     */
    public void generateScaledOutput(String inputPrefix, boolean noHeaders) {
        generateOutput(inputPrefix, true, noHeaders);
    }
}
