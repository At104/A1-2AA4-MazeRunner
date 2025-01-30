package ca.mcmaster.se2aa4.mazerunner.Maze;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.Position;

public class MazeSolver {
    private Maze maze;
    private MazeExplorer explorer;
    private Logger logger = LogManager.getLogger();
    
    public MazeSolver(Maze maze, MazeExplorer explorer) {
        this.maze = maze;
        this.explorer = explorer;
    }

    public boolean solve() {
        Position endPosition = maze.getEndPosition();
        logger.info("End position: " + endPosition);
        while (!explorer.getPosition().equals(endPosition)) {
            logger.info("Current position: " + explorer.getPosition());
            if (!explorer.moveForward()) {
                logger.info("No path found");
                return false; 
            }
        }
        logger.info("End position reached: " + explorer.getPosition());
        return true;
    }
}