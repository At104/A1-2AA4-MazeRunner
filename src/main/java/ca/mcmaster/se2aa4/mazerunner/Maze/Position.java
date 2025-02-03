package ca.mcmaster.se2aa4.mazerunner.Maze;

import ca.mcmaster.se2aa4.mazerunner.Path.Direction;

public class Position {
    private int row;
    private int col;

    /**
     * Constructor for Position
     * @param col
     * @param row
     */
    public Position(int col, int row) {
        this.row = row;
        this.col = col;
    }

    /**
     * Get the x coordinate of the position
     * @return {@code int} x coordinate (col)
     */
    public int getX() {
        return col;
    }

    /**
     * Get the y coordinate of the position
     * @return {@code int} y coordinate (row)
     */
    public int getY() {
        return row;
    }
    
    /**
     * Set the y coordinate of the position
     * @param row
     */
    public void setY(int row) {
        this.row = row;
    }

    /**
     * Set the x coordinate of the position
     * @param col
     */
    public void setX(int col) {
        this.col = col;
    }

    /**
     * Move the position in a given direction
     * @param direction
     * @return {@code Position} new position
     */
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

    /**
     * String representation of Position
     */
    @Override
    public String toString() {
        return "(" + row + ", " + col + ")";
    }

    /**
     * Hashcode for Position
     */
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