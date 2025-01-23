package ca.mcmaster.se2aa4.mazerunner;

public class MazeExplorer {
    private Position position;

    public MazeExplorer(Position startPosition) {
        this.position = startPosition;
    }

    public Position getPosition() {
        return position;
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
}