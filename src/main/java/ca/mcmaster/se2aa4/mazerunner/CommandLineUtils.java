package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.*;


public class CommandLineUtils {
    private CommandLine cmd;

    public CommandLineUtils(String[] args) throws ParseException {
        Options options = new Options();
        options.addOption("i", true, "Maze file argument");
        options.addOption("p", true, "Path to verify");

        CommandLineParser parser = new DefaultParser();
        this.cmd = parser.parse(options, args);
    }

    public boolean hasOption(String option) {
        return cmd.hasOption(option);
    }

    public String getOptionValue(String option) {
        return cmd.getOptionValue(option);
    }
}