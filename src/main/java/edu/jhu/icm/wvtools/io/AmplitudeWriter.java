package edu.jhu.icm.wvtools.io;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Writes amplitudes from interlaced data to an output stream.
 * Created by rliu14 on 6/15/2016.
 */
public class AmplitudeWriter {

    public void write(OutputStream os, List<Double>[] data, List<String> labels, int samplingFrequency, Date startTime, boolean noHeaders) {

        // Get max length of data.
        int maxLength = data[0].size();
        for (int c = 1; c < data.length; c++) {
            if (data[c].size() > maxLength) maxLength = data[c].size();
        }

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startTime);
        calendar.set(Calendar.MILLISECOND, 0);

        // Millisecond increment per data point
        double increment = 1000.0 / samplingFrequency;

        DateFormat format = new SimpleDateFormat("MM/dd/yy kk:mm:ss:SS");
        format.setTimeZone(TimeZone.getTimeZone("GMT"));

        PrintWriter printWriter = new PrintWriter(os);

        if (!noHeaders) {
            printWriter.print("Date\t");
            for (String s : labels) {
                printWriter.printf("%s\t", s);
            }
            printWriter.println();
        }

        for (int c = 0; c < maxLength; c++) {
            // Print date.
            printWriter.printf("%s\t", format.format(calendar.getTime()));
            calendar.add(Calendar.MILLISECOND, (int)increment);
            for (int d = 0; d < data.length; d++) {
                if (c < data[d].size()) {
                    printWriter.printf("%f\t", data[d].get(c));
                } else {
                    printWriter.print("\t");
                }
            }
            printWriter.println();
        }

        printWriter.flush();
        // Closing the stream is the caller's responsibility, not ours.

    }

}
