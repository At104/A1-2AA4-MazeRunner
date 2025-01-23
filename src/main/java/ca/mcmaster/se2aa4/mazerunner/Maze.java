package ca.mcmaster.se2aa4.mazerunner;

public class Maze {
    private Cell[][] cells;

    public Maze(Cell[][] cells) {
        this.cells = cells;
    }

    public Cell getCell(Position position) {
        return cells[position.getX()][position.getY()];
    }

    public int getRows() {
        return cells.length;
    }

    public int getCols() {
        return cells[0].length;
    }
}