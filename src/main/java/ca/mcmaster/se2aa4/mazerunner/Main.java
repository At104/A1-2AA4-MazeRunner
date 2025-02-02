package ca.mcmaster.se2aa4.mazerunner;

import ca.mcmaster.se2aa4.mazerunner.Maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.Maze.MazeInitializer;
import ca.mcmaster.se2aa4.mazerunner.Path.PathChecking;
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

                Maze maze = MazeInitializer.initializeMaze(filename);
                maze.printMaze();

                if (cmdHandler.hasOption("p")) {
                    String stringPath = cmdHandler.getOptionValue("p");
                    logger.info("Verifying path: " + stringPath);
                    MazeExplorer explorer = new MazeExplorer(maze);
                    boolean isValid = PathChecking.verifyPath(explorer, stringPath);
                    if (isValid) {
                        System.out.println("The path is valid.");
                    } 
                    else {
                        System.out.println("The path is invalid.");
                    }
                } 
                
                else {
                    logger.info("Computing path");
                    String path = PathChecking.computePath(maze);
                    if (path == null) {
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
        catch (Exception e) {
            logger.error("/!\\ An error has occured /!\\");
            e.printStackTrace();
        }
    }
}