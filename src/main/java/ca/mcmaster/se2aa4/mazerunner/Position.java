package ca.mcmaster.se2aa4.mazerunner;

public class Position {
    private int row;
    private int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getX() {
        return row;
    }

    public int getY() {
        return col;
    }

    public void setX(int row) {
        this.row = row;
    }

    public void setY(int col) {
        this.col = col;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
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