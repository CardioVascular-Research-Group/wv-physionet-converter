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
}
