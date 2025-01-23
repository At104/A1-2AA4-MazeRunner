package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        Options options = new Options();
        options.addOption("i", true, "Maze file argument");

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);

            if (cmd.hasOption("i")) {
                String filename = cmd.getOptionValue("i");
                logger.info("Reading the maze from file " + filename);
                BufferedReader reader = new BufferedReader(new FileReader(filename));
                String line;
                while ((line = reader.readLine()) != null) {
                    for (int idx = 0; idx < line.length(); idx++) {
                        if (line.charAt(idx) == '#') {
                            logger.info("WALL ");
                        } 
                        else if (line.charAt(idx) == ' ') {
                            logger.info("PASS ");
                        }
                    }
                    logger.info(System.lineSeparator());
                }
                reader.close();
            } 
            else {
                logger.error("Use -i flag to specify the maze file.");
            }
        } 
        catch (ParseException e) {
            logger.error("Failed to parse command line arguments", e);
        } 
        catch (Exception e) {
            logger.error("/!\\\\ An error has occured /!\\\\", e);
        }
        logger.info("Computing path");
        logger.info("PATH NOT COMPUTED");
        logger.info("End of MazeRunner");
    }
}