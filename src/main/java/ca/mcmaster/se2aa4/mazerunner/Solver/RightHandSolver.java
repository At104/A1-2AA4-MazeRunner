package ca.mcmaster.se2aa4.mazerunner.Solver;

import ca.mcmaster.se2aa4.mazerunner.Maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.Maze.Position;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RightHandSolver implements Solver {
    // Initialize the maze and explorer
    private Maze maze;
    private MazeExplorer explorer;
    private Logger logger = LogManager.getLogger();

    /**
     * Constructor for RightHandSolver
     * @param maze
     * @param explorer
     */
    public RightHandSolver(Maze maze, MazeExplorer explorer) {
        this.maze = maze;
        this.explorer = explorer;
    }

    /**
     * Solve the maze using the right-hand method
     * @return {@code boolean} Whether the maze was solved
     */
    @Override
    public boolean solve() {
        // Get the start and end positions
        Position endPosition = maze.getEndPosition();
        logger.info("Start position: " + explorer.getPosition());
        logger.info("End position: " + endPosition);

        while (!explorer.getPosition().equals(endPosition)) {
            logger.info("Current position: " + explorer.getPosition());
            logger.info("Current direction: " + explorer.getDirection());

            // Check if the cells around are walls
            Position leftPosition = explorer.getPosition().move(explorer.getDirection().turnLeft());
            Position rightPosition = explorer.getPosition().move(explorer.getDirection().turnRight());
            Position forwardPosition = explorer.getPosition().move(explorer.getDirection());

            // If the right cell is not a wall, turn right
            if (!maze.isWall(rightPosition)) {
                explorer.turnRight();      
            }
            // If the forward cell is a wall, turn left
            else if (maze.isWall(forwardPosition)) {
                if (maze.isWall(leftPosition)) {
                    explorer.turnRight();
                    explorer.turnRight();
                }
                else {
                    explorer.turnLeft();
                }
            }
            // Move forward after turning
            explorer.moveForward();
                
        }

        logger.info("End position reached: " + explorer.getPosition());
        return true;
    }
}