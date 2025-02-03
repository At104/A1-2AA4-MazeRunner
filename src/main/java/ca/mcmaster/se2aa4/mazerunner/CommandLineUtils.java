package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.*;


public class CommandLineUtils {
    private CommandLine cmd;

    public CommandLineUtils(String[] args) throws ParseException {
        // Create the command line options
        Options options = new Options();

        Option input =  new Option("i",  true, "Maze file argument");
        Option path = new Option("p", true, "Path to verify");
        input.setRequired(true);
        
        options.addOption(input);
        options.addOption(path);

        // Parse the command line arguments
        CommandLineParser parser = new DefaultParser();
        this.cmd = parser.parse(options, args);
    }
    /**
     * Check if the option is present
     * @param option
     * @return {@code boolean} True if the option is present, false otherwise
     */
    public boolean hasOption(String option) {
        return cmd.hasOption(option);
    }
    /**
     * Get the value of the option
     * @param option
     * @return {@code String} The value of the option
     */
    public String getOptionValue(String option) {
        return cmd.getOptionValue(option);
    }
}