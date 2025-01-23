package ca.mcmaster.se2aa4.mazerunner;

public class MazeExplorer {
    private Position position;
    private Maze maze;

    public MazeExplorer(Maze maze) {
        this.maze = maze;
        this.position = maze.getStartPosition();
    }
    
    public Position getPosition() {
        return this.position;
    }

    public void moveLeft() {
        this.position = new Position(position.getX() - 1, position.getY());
    }

    public void moveRight() {
        this.position = new Position(position.getX() + 1, position.getY());
    }

    public void moveUp() {
        this.position = new Position(position.getX(), position.getY() - 1);
    }

    public void moveDown() {
        this.position = new Position(position.getX(), position.getY() + 1);
    }

    private boolean makeMove(Position newPosition) {
        if (isValidMove(newPosition)) {
            this.position = newPosition;
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