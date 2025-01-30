package ca.mcmaster.se2aa4.mazerunner.Solver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.Direction;
import ca.mcmaster.se2aa4.mazerunner.Position;
import ca.mcmaster.se2aa4.mazerunner.Maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.Maze.MazeExplorer;

public class RightHandSolver implements Solver {    
    private Maze maze;
    private MazeExplorer explorer;
    private Logger logger = LogManager.getLogger();
    
    public RightHandSolver(Maze maze, MazeExplorer explorer) {
        this.maze = maze;
        this.explorer = explorer;
    }
    
    @Override
public boolean solve() {
    Position endPosition = maze.getEndPosition();
    Direction direction = explorer.getDirection();
    logger.info("Start position: " + explorer.getPosition());
    logger.info("End position: " + endPosition);

    while (!explorer.getPosition().equals(endPosition)) {
        logger.info("Current position: " + explorer.getPosition());
        logger.info("Current direction: " + direction);

        // Try to turn right and move forward
        explorer.turnRight();
        if (explorer.moveForward()) {
            logger.info("Turned right and moved forward");
        } 
        else {
            // If can't move forward, turn left (back to original direction)
            explorer.turnLeft();
            // Try to move forward
            if (explorer.moveForward()) {
                logger.info("Moved forward");
            } 
            else {
                // If can't move forward, turn left again (now facing left of original direction)
                explorer.turnLeft();
                // Try to move forward
                if (explorer.moveForward()) {
                    logger.info("Turned left and moved forward");
                } 
                else {
                    // If can't move forward, turn left again (now facing opposite of original direction)
                    explorer.turnLeft();
                    logger.info("Turned left again");
                }
            }
        }
    }

    logger.info("End position reached: " + explorer.getPosition());
    return true;
}
}