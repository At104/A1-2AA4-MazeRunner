package ca.mcmaster.se2aa4.mazerunner;

public enum Cell {
    WALL, PASS;

    public boolean isWall() {
        return this == WALL;
    }
}