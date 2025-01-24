package ca.mcmaster.se2aa4.mazerunner.Maze;


import ca.mcmaster.se2aa4.mazerunner.Direction;
import ca.mcmaster.se2aa4.mazerunner.Position;
import ca.mcmaster.se2aa4.mazerunner.Path.CanonicalPath;
import ca.mcmaster.se2aa4.mazerunner.Path.Path;

public class MazeExplorer {
    private Maze maze;
    private Position position;
    private Direction direction;
    private Path path;

    public MazeExplorer(Maze maze) {
        this.maze = maze;
        this.position = maze.getStartPosition();
        this.direction = Direction.RIGHT; 
        this.path = new CanonicalPath();
    }

    public Position getPosition() {
        return this.position;
    }

    public void turnRight() {
        direction = direction.turnRight();
        path.addInstruction('R');
    }

    public void turnLeft() {
        direction = direction.turnLeft();
        path.addInstruction('L');
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
            path.addInstruction('F');
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

    public String getPath() {
        return path.getInstructions();
    }
}