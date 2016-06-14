package edu.jhu.icm.wvtools.util;

/**
 * Calculates various checksums.
 * Created by ran on 6/14/16.
 */
public class ChecksumCalculator {

    private byte[] bytes;
    private short[] shorts;
    private int[] ints;
    private long[] longs;

    public ChecksumCalculator(int numChannels) {
        bytes = new byte[numChannels];
        shorts = new short[numChannels];
        ints = new int[numChannels];
        longs = new long[numChannels];
    }

    public void addByte(byte value, int channel) {
        bytes[channel] += value;
        shorts[channel] += value;
        ints[channel] += value;
        longs[channel] += value;
    }

    public void addShort(short value, int channel) {
        bytes[channel] += value;
        shorts[channel] += value;
        ints[channel] += value;
        longs[channel] += value;
    }

    public void addInt(int value, int channel) {
        bytes[channel] += value;
        shorts[channel] += value;
        ints[channel] += value;
        longs[channel] += value;
    }

    public void addLong(long value, int channel) {
        bytes[channel] += value;
        shorts[channel] += value;
        ints[channel] += value;
        longs[channel] += value;
    }

    public byte getByte(int channel) {
        return bytes[channel];
    }

    public short getShort(int channel) {
        return shorts[channel];
    }

    public int getInt(int channel) {
        return ints[channel];
    }

    public long getLong(int channel) {
        return longs[channel];
    }

}
