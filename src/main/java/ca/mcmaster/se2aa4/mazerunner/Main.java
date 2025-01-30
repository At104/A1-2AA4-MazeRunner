package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.Maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.Maze.MazeExplorer;
import ca.mcmaster.se2aa4.mazerunner.Maze.MazeInitializer;
import ca.mcmaster.se2aa4.mazerunner.Solver.RightHandSolver;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        Options options = new Options();
        options.addOption("i", true, "Maze file argument");

        CommandLineParser parser = new DefaultParser();

        //TODO: Implement the abstractions once the code is refactored
        try {
            CommandLine cmd = parser.parse(options, args);

            if (cmd.hasOption("i")) {
                String filename = cmd.getOptionValue("i");
                logger.info("Start of MazeRunner");
                logger.info("Reading the maze from file " + filename);
                
                Maze maze = MazeInitializer.initializeMaze(filename);
                maze.printMaze();

                
                MazeExplorer explorer = new MazeExplorer(maze);
                logger.info("Computing path");
                RightHandSolver solver = new RightHandSolver(maze, explorer);

                if (solver.solve()) {
                    System.out.println("Path computed: " + explorer.getPath());
                } 
                else {
                    logger.info("No path found");
                }
                
                
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
            logger.warn("PATH NOT COMPUTED");

            e.printStackTrace();
        }
        
        logger.info("End of MazeRunner");
    }
}