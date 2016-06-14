package edu.jhu.icm.wvtools.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Reads data from info file.
 * Created by ran on 6/14/16.
 */
public class InfoReader {

    private int sampleRate;
    private List<String> units;
    private List<String> channelNames;
    private List<Double> gains;
    private Date startDate;

    // Compiling regex is time-consuming; do it once, even if class is initialized multiple times.
    private static final Pattern channelInfoPattern = Pattern.compile("([0-9a-zA-Z\\-.]+) (\\d+) (\\d+) ([0-9a-zA-Z]+) \"([0-9a-zA-Z]+)\" gain=(\\d.\\d+)");
    private static final Pattern datePattern = Pattern.compile("\\d+ (\\d+)\\-(\\d+)\\-(\\d+)");
    private static final Pattern timePattern = Pattern.compile("\\d+ (\\d+):(\\d+):(\\d+)");

    /**
     * Constructs a reader to read from a file.
     * @param prefix File prefix to read from.
     */
    public InfoReader(String prefix) throws FileNotFoundException, InfoReaderException {
        units = new ArrayList<>();
        channelNames = new ArrayList<>();
        gains = new ArrayList<>();

        // Read in data from info file.
        Scanner scanner = new Scanner(new File(prefix + ".info"));
        // Let's use regex to capture our desired information.

        while (scanner.hasNextLine()) {
            String currentLine = scanner.nextLine();
            if (currentLine.length() == 0) break;

            Matcher channelInfoMatcher = channelInfoPattern.matcher(currentLine);
            if (!channelInfoMatcher.find()) throw new InfoReaderException("Could not parse info file.");

            sampleRate = Integer.parseInt(channelInfoMatcher.group(2));
            units.add(channelInfoMatcher.group(4));
            channelNames.add(channelInfoMatcher.group(5));
            gains.add(Double.parseDouble(channelInfoMatcher.group(6)));
        }
        scanner.close();


        // Read in file from time annotation file.
        Scanner timeScanner = new Scanner(new File(prefix + ".time.txt"));
        String dateLine = timeScanner.nextLine();
        String timeLine = timeScanner.nextLine();

        Matcher dateMatcher = datePattern.matcher(dateLine);
        Matcher timeMatcher = timePattern.matcher(timeLine);
        if (!dateMatcher.find() || !timeMatcher.find()) throw new InfoReaderException("Could not parse date annotation file.");

        Calendar calendar = new GregorianCalendar();
        // Note that Java Calendar months are 0-indexed; hence, we subtract one.
        calendar.set(Integer.parseInt(dateMatcher.group(1)), (Integer.parseInt(dateMatcher.group(2)) - 1),
                Integer.parseInt(dateMatcher.group(3)), Integer.parseInt(timeMatcher.group(1)),
                Integer.parseInt(timeMatcher.group(2)), Integer.parseInt(timeMatcher.group(3)));
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));

        startDate = calendar.getTime();

        timeScanner.close();
    }

    /**
     * Gets sample rate.
     * @return Sampling rate.
     */
    public int getSampleRate() {
        return sampleRate;
    }

    /**
     * Gets units for each channel.
     * @return List of units for each channel.
     */
    public List<String> getUnits() {
        return units;
    }

    /**
     * Gets names for each channel.
     * @return List of names for each channel.
     */
    public List<String> getChannelNames() {
        return channelNames;
    }

    /**
     * Gets gains for each channel.
     * @return List of gains for each channel.
     */
    public List<Double> getGains() {
        return gains;
    }

    /**
     * Gets timestamp of the first data point in the file.
     * @return Date object containing timestamp of first data point in file.
     */
    public Date getStartDate() {
        return startDate;
    }

}
