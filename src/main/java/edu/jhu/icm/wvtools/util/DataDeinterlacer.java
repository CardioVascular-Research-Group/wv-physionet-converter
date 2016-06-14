package edu.jhu.icm.wvtools.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Deinterlaces data into the number of channels specificed.
 * Created by ran on 6/14/16.
 */
public class DataDeinterlacer {

    @SuppressWarnings("unchecked")
    public List<Byte>[] deinterlaceBytes(List<Byte> bytes, int numChannels) {
        List<Byte>[] data = new List[numChannels]; // Ah, type erasure my old nemesis
        for (int c = 0; c < numChannels; c++) {
            data[c] = new ArrayList<>();
        }

        int currentCounter = 0;
        for (Byte b : bytes) {
            data[currentCounter % numChannels].add(b);
            currentCounter++;
        }
        return data;
    }

    @SuppressWarnings("unchecked")
    public List<Short>[] deinterlaceShorts(List<Short> shorts, int numChannels) {
        List<Short>[] data = new List[numChannels];
        for (int c = 0; c < numChannels; c++) {
            data[c] = new ArrayList<>();
        }

        int currentCounter = 0;
        for (Short s : shorts) {
            data[currentCounter % numChannels].add(s);
            currentCounter++;
        }
        return data;
    }

    @SuppressWarnings("unchecked")
    public List<Integer>[] deinterlaceInts(List<Integer> integers, int numChannels) {
        List<Integer>[] data = new List[numChannels];
        for (int c = 0; c < numChannels; c++) {
            data[c] = new ArrayList<>();
        }

        int currentCounter = 0;
        for (Integer i : integers) {
            data[currentCounter % numChannels].add(i);
            currentCounter++;
        }
        return data;
    }

    @SuppressWarnings("unchecked")
    public List<Long>[] deinterlaceLongs(List<Long> longs, int numChannels) {
        List<Long>[] data = new List[numChannels];
        for (int c = 0; c < numChannels; c++) {
            data[c] = new ArrayList<>();
        }

        int currentCounter = 0;
        for (Long l : longs) {
            data[currentCounter % numChannels].add(l);
            currentCounter++;
        }
        return data;
    }
}
