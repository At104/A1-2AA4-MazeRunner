package ca.mcmaster.se2aa4.mazerunner;

import ca.mcmaster.se2aa4.mazerunner.Maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.Maze.MazeInitializer;
import ca.mcmaster.se2aa4.mazerunner.PathChecking.CanonicalPathChecking;
import ca.mcmaster.se2aa4.mazerunner.PathChecking.FactorizedPathChecking;
import ca.mcmaster.se2aa4.mazerunner.PathChecking.PathChecking;
import ca.mcmaster.se2aa4.mazerunner.Solver.MazeExplorer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.ParseException;

import java.io.IOException;

public class Main {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) throws IOException {
        try {
            CommandLineUtils cmdHandler = new CommandLineUtils(args);

            if (cmdHandler.hasOption("i")) {
                String filename = cmdHandler.getOptionValue("i");
                logger.info("Start of MazeRunner");
                logger.info("Reading the maze from file " + filename);
                // Initialize the maze and the classes to solve it
                MazeInitializer initializer = new MazeInitializer();
                Maze maze = initializer.initializeMaze(filename);
                MazeExplorer explorer = new MazeExplorer(maze);
                PathChecking checker;

                if (cmdHandler.hasOption("p")) {
                    String stringPath = cmdHandler.getOptionValue("p").replaceAll("\\s+","");
                    logger.info("Verifying path: " + stringPath);
                    
                    if (PathChecking.isFactorizedPath(stringPath)) {
                        logger.info("Verifying factorized path");
                        checker = new FactorizedPathChecking(maze, explorer);
                    } 
                    else {
                        logger.info("Verifying canonical path");
                        checker = new CanonicalPathChecking(maze, explorer);
                    }

                    boolean isValid = checker.verifyPath(stringPath);

                    if (isValid) {
                        System.out.println("The path is valid.");
                    } 
                    else {
                        System.out.println("The path is invalid.");
                    }
                } 

                else {
                    logger.info("Computing path");
                    checker = new FactorizedPathChecking(maze, explorer);
                    String path = checker.computePath();
                    if (path.equals("No path found")) {
                        System.out.println("No path found.");
                    } 
                    else {
                        System.out.println("Path computed: " + path);
                    }
                }
            }
        } 
        catch (ParseException e) {
            logger.error("Failed to parse command line arguments", e);
        }
    }
}