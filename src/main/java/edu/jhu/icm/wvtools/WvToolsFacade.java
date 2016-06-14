package edu.jhu.icm.wvtools;

import edu.jhu.icm.wvtools.io.InfoReader;
import edu.jhu.icm.wvtools.io.InfoReaderException;
import edu.jhu.icm.wvtools.io.WvReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Facade that dispatches calls from driver.
 * Created by ran on 6/14/16.
 */
class WvToolsFacade {

    void generateRawOutput(String inputPrefix, boolean noHeaders) {
        try {
            InfoReader reader = new InfoReader(inputPrefix);

        } catch (FileNotFoundException | InfoReaderException e) {

        }
    }

    void generatePhysioNetOutput(String inputPrefix) {

    }

    void generateScaledOutput(String inputPrefix, boolean noHeaders) {

    }
}
