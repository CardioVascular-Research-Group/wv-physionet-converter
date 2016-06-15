package edu.jhu.icm.wvtools.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Scales data.
 * Created by rliu14 on 6/15/2016.
 */
public class DataScaler {

    /**
     * Scales all numbers in the list by the factor specified.
     * @param data Data to scale.
     * @param factor Scaling factor.
     * @return List of scaled numbers, in double precision.
     */
    public List<Double> scaleShorts(List<Short> data, double factor) {
        List<Double> results = new ArrayList<>();

        for (Short n : data) {
            results.add(factor * n.doubleValue());
        }
        return results;
    }

    /**
     * Scales multi-channel data according to the factors specified.
     * @param data Data to scale.
     * @param factors Scaling factors.
     * @return Array of lists of scaled numbers, in double precision.
     */
    @SuppressWarnings("unchecked")
    public List<Double>[] scaleShorts(List<Short>[] data, List<Double> factors) {
        List<Double>[] results = new List[data.length];
        for (int c = 0; c < data.length; c++) {
            results[c] = new ArrayList<>();
            for (Short s : data[c]) {
                results[c].add(factors.get(c) * s.doubleValue());
            }
        }
        return results;
    }

    /**
     * Scales all channels of multi-channel data by a single factor.
     * @param data Data to scale.
     * @param factor Scaling factor.
     * @return Array of lists of scaled numbers, in double precision.
     */
    @SuppressWarnings("unchecked")
    public List<Double>[] scaleShorts(List<Short>[] data, double factor) {
        List<Double>[] results = new List[data.length];
        for (int c = 0; c < data.length; c++) {
            results[c] = new ArrayList<>();
            for (Short s : data[c]) {
                results[c].add(factor * s.doubleValue());
            }
        }
        return results;
    }
}
