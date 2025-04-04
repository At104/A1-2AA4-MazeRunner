package ca.mcmaster.se2aa4.mazerunner.Maze;

import java.util.Iterator;

public class ColumnIterator implements Iterator<Cell> {
    private final Cell[][] maze;
    private int currentRow;
    private int currentCol;
    private final int rows;
    private final int cols;

    public ColumnIterator(Cell[][] maze) {
        this.maze = maze;
        this.rows = maze.length;
        this.cols = maze[0].length;
        this.currentRow = 0;
        this.currentCol = 0;
    }

    @Override
    public boolean hasNext() {
        return currentCol < cols && currentRow < rows;
    }

    @Override
    public Cell next() {
        Cell cell = maze[currentRow][currentCol];
        currentRow++;
        if (currentRow >= rows) {
            currentRow = 0;
            currentCol++;
        }
        return cell;
    }
} 