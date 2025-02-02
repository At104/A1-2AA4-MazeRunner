package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.*;


public class CommandLineUtils {
    private CommandLine cmd;

    public CommandLineUtils(String[] args) throws ParseException {
        Options options = new Options();

        Option input =  new Option("i",  true, "Maze file argument");
        Option path = new Option("p", true, "Path to verify");
        input.setRequired(true);
        
        options.addOption(input);
        options.addOption(path);

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