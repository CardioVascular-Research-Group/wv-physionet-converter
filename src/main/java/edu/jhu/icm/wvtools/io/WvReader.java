package edu.jhu.icm.wvtools.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads data from a WV file (or any 16-bit signed little-endian file) as a sequence of shorts.
 * Created by ran on 6/14/16.
 */
public class WvReader {

    private FileInputStream inputStream;

    /**
     * Construct a reader to read from file.
     * @param file File to read from.
     * @throws FileNotFoundException File not found.
     */
    public WvReader(File file) throws FileNotFoundException {
        inputStream = new FileInputStream(file);
    }

    /**
     * Next signed value in the file.
     * @return Next signed value, if one is to be found. Null if end of file reached.
     * @throws IOException If I/O error encountered.
     */
    private Short nextShort() throws IOException {
        int lowerByte = inputStream.read();
        int upperByte = inputStream.read();

        if (lowerByte == -1 || upperByte == -1) return null;
        return littleEndianConversion(lowerByte, upperByte);
    }

    /**
     * Closes the input stream.
     */
    private void close() throws IOException {
        inputStream.close();
    }

    /**
     * Returns all shorts from file.
     * @return List of shorts.
     * @throws IOException
     */
    public List<Short> allShorts() throws IOException {
        List<Short> result = new ArrayList<>();
        Short currentValue;
        while ((currentValue = nextShort()) != null) {
            result.add(currentValue);
        }
        close();
        return result;
    }

    /**
     * Converts two bytes to a short.
     * @param lower Int containing the lower byte.
     * @param upper Int containing the upper byte.
     * @return Short value.
     */
    private static short littleEndianConversion(int lower, int upper) {
        return (short)(((0xff & upper) << 8) | (lower & 0xff));
    }

}
