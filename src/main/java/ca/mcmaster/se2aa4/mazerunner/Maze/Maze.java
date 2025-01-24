package ca.mcmaster.se2aa4.mazerunner.Maze;

import ca.mcmaster.se2aa4.mazerunner.Cell;
import ca.mcmaster.se2aa4.mazerunner.Position;

public class Maze {
    private Cell[][] cells;
    private Position startPosition;
    private Position endPosition;
    private char[][] mazeData;

    public Maze(char[][] mazeData) {
        int rows = mazeData.length;
        int cols = mazeData[0].length;
        cells = new Cell[rows][cols];
        this.mazeData = mazeData;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (mazeData[i][j] == '#') {
                    cells[i][j] = Cell.WALL;
                } else {
                    cells[i][j] = Cell.PASS;
                }
            }
        }

        this.startPosition = findStartPosition();
        this.endPosition = findEndPosition();
    }

    private Position findStartPosition() {
        for (int i = 0; i < cells.length; i++) {
            if (cells[i][0] == Cell.PASS) {
                return new Position(i, 0);
            }
        }
        return null;
    }

    private Position findEndPosition() {
        int lastCol = cells[0].length - 1;
        for (int i = 0; i < cells.length; i++) {
            if (cells[i][lastCol] == Cell.PASS) {
                return new Position(i, lastCol);
            }
        }
        return null;
    }

    public Cell getCell(Position position) {
        return cells[position.getX()][position.getY()];
    }

    public boolean isWall(Position position) {
        return getCell(position).isWall();
    }

    public Position getStartPosition() {
        return startPosition;
    }

    public Position getEndPosition() {
        return endPosition;
    }

    public Position getDimensions() {
        return new Position(cells.length, cells[0].length);
    }

    public char[][] getMazeData() {
        return mazeData;
    }
}