package ca.mcmaster.se2aa4.mazerunner.Maze;

import ca.mcmaster.se2aa4.mazerunner.Path.Direction;

public class Position {
    private int row;
    private int col;

    public Position(int col, int row) {
        this.row = row;
        this.col = col;
    }


    public int getX() {
        return col;
    }

    public int getY() {
        return row;
    }

    public void setY(int row) {
        this.row = row;
    }

    public void setX(int col) {
        this.col = col;
    }


    public Position move(Direction direction) {
        switch (direction) {
            case UP:
                return new Position(getX() , getY() -1);
            case RIGHT:
                return new Position(getX() +1 , getY());
            case DOWN:
                return new Position(getX(), getY() +1);
            case LEFT:
                return new Position(getX()-1, getY());
            default:
                return null; // Should never reach here, but needed to compile
        }
    }
    @Override
    public String toString() {
        return "(" + row + ", " + col + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Position other = (Position) obj;
        if (col != other.col) {
            return false;
        }
        if (row != other.row) {
            return false;
        }
        return true;
    }
}