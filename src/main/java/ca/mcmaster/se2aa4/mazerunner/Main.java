package ca.mcmaster.se2aa4.mazerunner;

import ca.mcmaster.se2aa4.mazerunner.Maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.Maze.MazeInitializer;
import ca.mcmaster.se2aa4.mazerunner.Solver.MazeExplorer;
import ca.mcmaster.se2aa4.mazerunner.Solver.RightHandSolver;
import ca.mcmaster.se2aa4.mazerunner.Path.CanonicalPath;
import ca.mcmaster.se2aa4.mazerunner.Path.Path;

import java.io.IOException;

import org.apache.commons.cli.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) throws IOException {
        Options options = new Options();
        options.addOption("i", true, "Maze file argument");
        options.addOption("p", true, "Path to verify");

        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine cmd = parser.parse(options, args);

            if (cmd.hasOption("i")) {
                String filename = cmd.getOptionValue("i");
                logger.info("Start of MazeRunner");
                logger.info("Reading the maze from file " + filename);

                Maze maze = MazeInitializer.initializeMaze(filename);
                maze.printMaze();

                if (cmd.hasOption("p")) {
                    String stringPath = cmd.getOptionValue("p");
                    logger.info("Verifying path: " + stringPath);
                    MazeExplorer explorer = new MazeExplorer(maze);
                    Path path = new CanonicalPath(stringPath);
                    boolean isValid = path.verifyPath(explorer);
                    if (isValid) {
                        System.out.println("The path is valid.");
                    } 
                    else {
                        System.out.println("The path is invalid.");
                    }
                } 
                else {
                    logger.info("Computing path");
                    MazeExplorer explorer = new MazeExplorer(maze);
                    RightHandSolver solver = new RightHandSolver(maze, explorer);

                    if (solver.solve()) {
                        System.out.println("Path computed: " + explorer.getPath());
                    } 
                    else {
                        logger.info("No path found");
                    }
                }
            }
        } catch (ParseException e) {
            logger.error("Failed to parse command line arguments", e);
        }
    }
}