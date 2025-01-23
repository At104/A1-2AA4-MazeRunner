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
}