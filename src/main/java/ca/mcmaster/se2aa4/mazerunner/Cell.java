package ca.mcmaster.se2aa4.mazerunner;

public class Cell {
    private boolean isWall;

    public Cell(boolean isWall) {
        this.isWall = isWall;
    }

    public boolean isWall() {
        return isWall; //# on maze
    }
}