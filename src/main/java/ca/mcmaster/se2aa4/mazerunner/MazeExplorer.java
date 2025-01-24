package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;

public class MazeExplorer {
    private Maze maze;
    private Position position;
    private Direction direction;
    private List<Position> path;

    public MazeExplorer(Maze maze) {
        this.maze = maze;
        this.position = maze.getStartPosition();
        this.direction = Direction.RIGHT; // Assume starting direction is right
        this.path = new ArrayList<>();
        this.path.add(this.position);
    }

    public Position getPosition() {
        return this.position;
    }

    public void turnRight() {
        direction = direction.turnRight();
    }

    public void turnLeft() {
        direction = direction.turnLeft();
    }

    public boolean moveForward() {
        Position newPosition = null;
        switch (direction) {
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
            this.position = newPosition;
            this.path.add(newPosition);
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

    public boolean solve() {
        Position endPosition = maze.getEndPosition();
        while (!position.equals(endPosition)) {
            if (!moveForward()) {
                turnRight();
                if (!moveForward()) {
                    turnLeft();
                    turnLeft();
                    if (!moveForward()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void printPath() {
        char[][] mazeData = maze.getMazeData();
        for (Position pos : path) {
            mazeData[pos.getX()][pos.getY()] = '*';
        }
        for (char[] row : mazeData) {
            System.out.println(new String(row));
        }
    }
}