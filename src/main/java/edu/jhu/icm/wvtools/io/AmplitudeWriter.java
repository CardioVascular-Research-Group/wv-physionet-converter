package edu.jhu.icm.wvtools.io;

import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Writes amplitudes from interlaced data to an output stream.
 * Created by rliu14 on 6/15/2016.
 */
public class AmplitudeWriter {

    public void write(OutputStream os, List<Double>[] data, int samplingFrequency, Date startTime, boolean noHeaders) {

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startTime);



        calendar.add(Calendar.MILLISECOND, 0);

    }

}
