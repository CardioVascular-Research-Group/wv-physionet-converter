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
     * Initializes a header writer with the info read from the info file infoReader.
     * @param infoReader Info file infoReader.
     */
    public PhysioNetHeaderWriter(InfoReader infoReader, WvReader wvReader) {
        this.infoReader = infoReader;
    }


    /**
     * Writes header to specified output stream.
     * @param os Output stream to write.
     */
    public void write(OutputStream os) {

    }


}
