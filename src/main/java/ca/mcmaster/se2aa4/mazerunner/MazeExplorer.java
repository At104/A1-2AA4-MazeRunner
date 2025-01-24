package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;

public class MazeExplorer {
    private Maze maze;
    private Position position;
    private Direction direction;

    public MazeExplorer(Maze maze) {
        this.maze = maze;
        this.position = maze.getStartPosition();
        this.direction = new Direction(Direction.DirectionType.RIGHT); // Assume starting direction is right
    }

    public Position getPosition() {
        return this.position;
    }

    public void turnRight() {
        direction.turnRight();
    }

    public void turnLeft() {
        direction.turnLeft();
    }

    public boolean moveForward() {
        Position newPosition = null;
        switch (direction.getDirection()) {
            case UP:
                newPosition = new Position(position.getX(), position.getY() - 1);
                break;
            case RIGHT:
                newPosition = new Position(position.getX() + 1, position.getY());
                break;
            case DOWN:
                newPosition = new Position(position.getX(), position.getY() + 1);
                break;
            case LEFT:
                newPosition = new Position(position.getX() - 1, position.getY());
                break;
        }
        if (isValidMove(newPosition)) {
            position = newPosition;
            return true;
        }
        return false;
    }

    private boolean isValidMove(Position position) {
        int x = position.getX();
        int y = position.getY();
        return x >= 0 && x < maze.getDimensions().getX() &&
               y >= 0 && y < maze.getDimensions().getY() &&
               !maze.isWall(position);
    }

    

   
}