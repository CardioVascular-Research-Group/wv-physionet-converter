package edu.jhu.icm.wvtools;

import org.apache.commons.cli.*;

/**
 * Main class of WVTools.
 * Configures CLI, then passes necessary parameters via facade.
 * Created by ran on 6/14/16.
 */
public class Driver {

    private static Options options = new Options();

    private static void configureOptions() {
        Option inputFile = new Option("f", "input-file", true, "Input file prefix, without extension");
        inputFile.setRequired(true);
        options.addOption(inputFile);

        options.addOption("s", "scaled", false, "Scaled amplitude output (default)");
        options.addOption("r", "raw", false, "Raw integer amplitude output (not scaled to units)");
        options.addOption("p", "physionet", false, "PhysioNet output");
        options.addOption("h", "no-headers", false, "Omit headers when generating amplitude output files");
    }

    public static void main(String[] args) {
        configureOptions();

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);

            String inputPrefix = cmd.getOptionValue("input-file");

            boolean raw = cmd.hasOption("raw");
            boolean physionet = cmd.hasOption("physionet");
            boolean noHeaders = cmd.hasOption("no-headers");

            WvToolsFacade facade = new WvToolsFacade();

            // Could check conflicts between function flags. We leave it up to the user not to though.

            if (raw) {
                facade.generateRawOutput(inputPrefix, noHeaders);
            } else if (physionet) {
                facade.generatePhysioNetOutput(inputPrefix);
            } else {
                // Default setting is to generate scaled data.
                facade.generateScaledOutput(inputPrefix, noHeaders);
            }

        } catch (ParseException e) {
            System.err.println(e.getMessage());
            HelpFormatter help = new HelpFormatter();
            help.printHelp("java -jar wvtools.jar", options);
        }

    }
}
