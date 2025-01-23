package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MazeInitializer {
    private final static Logger logger = LogManager.getLogger();

    public static Maze initializeMaze(String file) throws Exception {
        
        logger.trace("Start of MazeInitializer");
        logger.info("Reading the maze from file " + file);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        
        char[][] readMaze = reader.lines().map(String::toCharArray).toArray(char[][]::new);
        
        reader.close();
        Maze maze = new Maze(readMaze);
        logger.trace("End of MazeInitializer");
        return maze;
    }
}
