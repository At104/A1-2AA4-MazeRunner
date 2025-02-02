package ca.mcmaster.se2aa4.mazerunner.Solver;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.Maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.Maze.Position;
import ca.mcmaster.se2aa4.mazerunner.Path.Path;
import ca.mcmaster.se2aa4.mazerunner.Path.Direction;
import ca.mcmaster.se2aa4.mazerunner.Path.FactorizedPath;

public class MazeExplorer {
    private Maze maze;
    private Position position;
    private Direction direction;
    private Path path;

    private Logger logger = LogManager.getLogger();

    public MazeExplorer(Maze maze) {
        this.maze = maze;
        this.position = maze.getStartPosition();
        this.direction = Direction.RIGHT; 
        this.path = new FactorizedPath();
    }

    public Position getPosition() {
        return this.position;
    }

    public void turnRight() {
        direction = direction.turnRight();
        logger.info("Turning right");
        path.addInstruction('R');
    }

    public Direction getDirection() {
        return this.direction;
    }

    public String getPath() {
        return path.getInstructions();
    }

    public Maze getMaze() {
        return this.maze;
    }
    
    public void turnLeft() {
        direction = direction.turnLeft();
        logger.info("Turning left");
        path.addInstruction('L');
    }

    private boolean isValidMove(Position position) {
        int x = position.getX();
        int y = position.getY();
        logger.info("Is wall: " + maze.isWall(position));
        return x >= 0 && x < maze.getDimensions().getX() &&
               y >= 0 && y < maze.getDimensions().getY() &&
               !maze.isWall(position);
    }

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