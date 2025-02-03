package ca.mcmaster.se2aa4.mazerunner.Solver;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.Maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.Maze.Position;
import ca.mcmaster.se2aa4.mazerunner.Path.Path;
import ca.mcmaster.se2aa4.mazerunner.Path.Direction;
import ca.mcmaster.se2aa4.mazerunner.Path.FactorizedPath;

public class MazeExplorer {
    // Initialize the maze, position, direction, and path
    private Maze maze;
    private Position position;
    private Direction direction;
    private Path path;

    private Logger logger = LogManager.getLogger();

    /**
     * Constructor for MazeExplorer
     * @param maze
     */
    public MazeExplorer(Maze maze) {
        this.maze = maze;
        this.position = maze.getStartPosition();
        this.direction = Direction.RIGHT; 
        this.path = new FactorizedPath();
    }
    /**
     * Get the current position of the explorer
     * @return {@code Position} The current position
     */
    public Position getPosition() {
        return this.position;
    }

    /**
     * Turn the explorer right
     */
    public void turnRight() {
        direction = direction.turnRight();
        logger.info("Turning right");
        path.addInstruction('R');
    }
    /**
     * Turn the explorer left
     */
    public void turnLeft() {
        direction = direction.turnLeft();
        logger.info("Turning left");
        path.addInstruction('L');
    }

    /**
     * Get the current direction of the explorer
     * @return {@code Direction} The current direction
     */ 
    public Direction getDirection() {
        return this.direction;
    }
    /**
     * Get the path of the explorer
     * @return {@code String} The path
     */
    public String getPathInstructions() {
        return path.getInstructions();
    }
    /**
     * Get the maze
     * @return {@code Maze} The maze
     */
    public Maze getMaze() {
        return this.maze;
    }

    
    /**
     * Check if the move is valid by finding the bounds of the maze and checking if the position is a wall
     * @param position The position to check
     * @return {@code boolean} True if the move is valid, false otherwise
     */
    private boolean isValidMove(Position position) {
        int x = position.getX();
        int y = position.getY();
        logger.info("Is wall: " + maze.isWall(position));
        return x >= 0 && x < maze.getDimensions().getX() &&
               y >= 0 && y < maze.getDimensions().getY() &&
               !maze.isWall(position);
    }
    /**
     * Move the explorer forward
     * @return {@code boolean} True if the explorer moved forward, false otherwise
     */
    public boolean moveForward() {
        Position newPosition = position.move(direction);
        if (isValidMove(newPosition)) {
            this.position = newPosition;
            logger.info("Going forward");
            path.addInstruction('F');
            return true;
        }
        logger.info("Move is invalid because of wall at " + newPosition);
        return false;
    }


   
}