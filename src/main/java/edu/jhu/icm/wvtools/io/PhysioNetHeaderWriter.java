package edu.jhu.icm.wvtools.io;

import edu.jhu.icm.wvtools.util.ChecksumCalculator;
import edu.jhu.icm.wvtools.util.DataDeinterlacer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

/**
 * Writes PhysioNet header files.
 * Created by rliu14 on 6/15/2016.
 */
public class PhysioNetHeaderWriter {

    private String prefix;
    private InfoReader infoReader;
    private WvReader wvReader;

    /**
     * Initializes a writer with data about a wv entry.
     * @param infoReader InfoReader with data from info file.
     * @param wvReader WvReader with waveform data.
     */
    public PhysioNetHeaderWriter(String prefix, InfoReader infoReader, WvReader wvReader) {
        this.prefix = prefix;
        this.infoReader = infoReader;
        this.wvReader = wvReader;
    }


    /**
     * Writes header to specified output stream.
     * @param os Output stream to write.
     * @throws IOException IO problem encountered in reading from wv file.
     */
    public void write(OutputStream os) throws IOException {
        PrintWriter writer = new PrintWriter(os);

        // Calculate checksums for each channel.
        ChecksumCalculator calculator = new ChecksumCalculator(infoReader.getNumChannels());
        DataDeinterlacer ddi = new DataDeinterlacer();
        List<Short>[] deinterlacedData = ddi.deinterlaceShorts(wvReader.allShorts(), infoReader.getNumChannels());
        for (int c = 0; c < deinterlacedData.length; c++) {
            for (Short s : deinterlacedData[c]) {
                calculator.addShort(s, c);
            }
        }

        // Record line: name # signals, # samples per signal
        writer.printf("%s %d %d\n", prefix, infoReader.getNumChannels(), wvReader.allShorts().size() / infoReader.getNumChannels());
        for (int c = 0; c < infoReader.getNumChannels(); c++) {
            writer.printf("%s.dat 16 %f/%s 16 0 0 %d 0 %s\n", prefix, 1.0 / infoReader.getGains().get(c), infoReader.getUnits().get(c), calculator.getShort(c), infoReader.getChannelNames().get(c));
        }

        writer.flush();
        // Again, as we were passed the stream, it is the caller's responsibility to close it.
    }


}
