package edu.jhu.icm.wvtools.io;

import java.io.OutputStream;

/**
 * Writes PhysioNet header files.
 * Created by rliu14 on 6/15/2016.
 */
public class PhysioNetHeaderWriter {

    private InfoReader infoReader;
    private WvReader wvReader;

    /**
     * Initializes a writer with data about a wv entry.
     * @param infoReader InfoReader with data from info file.
     * @param wvReader WvReader with waveform data.
     */
    public PhysioNetHeaderWriter(InfoReader infoReader, WvReader wvReader) {
        this.infoReader = infoReader;
        this.wvReader = wvReader;
    }


    /**
     * Writes header to specified output stream.
     * @param os Output stream to write.
     */
    public void write(OutputStream os) {

    }


}
