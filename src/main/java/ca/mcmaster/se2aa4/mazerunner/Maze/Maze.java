package ca.mcmaster.se2aa4.mazerunner.Maze;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Maze {
    private static final Logger logger = LogManager.getLogger();

    private Position startPosition;
    private Position endPosition;
    private Cell[][] maze;

    public Maze(char[][] mazeData) {
        int rows = mazeData.length;
        int cols = mazeData[0].length;
        this.maze = new Cell[rows][cols];

        logger.info("Initializing maze with dimensions: {}x{}", rows, cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (mazeData[i][j] == '#') {
                    this.maze[i][j] = Cell.WALL;
                } 
                else if (mazeData[i][j] == ' ') {
                    this.maze[i][j] = Cell.PASS;
                }
            }
        }

        

        this.startPosition = findStartPosition();
        this.endPosition = findEndPosition();

    }

    private Position findStartPosition() {
        for (int i = 0; i < this.maze.length; i++) {
            if (this.maze[i][0] == Cell.PASS) {
                logger.info("Found start position at: ({}, 0)", i);
                return new Position(0,i);
            }
        }
        throw new IllegalArgumentException("No start position found in the first column.");
    }

    private Position findEndPosition() {
        int lastCol = this.maze[0].length - 1;
        for (int i = 0; i < this.maze.length; i++) {
            if (this.maze[i][lastCol] == Cell.PASS) {
                logger.info("Found end position at: ({}, {})", i, lastCol);
                return new Position(lastCol,i);
            }
        }
        throw new IllegalArgumentException("No end position found in the last column.");
    }

    public Cell getCell(Position position) {
        return this.maze[position.getY()][position.getX()];
    }

    public boolean isWall(Position position) {
        return getCell(position) == Cell.WALL;
    }

    public Position getStartPosition() {
        return startPosition;
    }

    public Position getEndPosition() {
        return endPosition;
    }

    public Position getDimensions() {
        return new Position(this.maze[0].length,this.maze.length);
    }

  
    public void printMaze() {
        for (int i = 0; i < this.maze.length; i++) {
            for (int j = 0; j < this.maze[i].length; j++) {
                if (this.maze[i][j] == Cell.WALL) {
                    System.out.print("WALL ");
                } else {
                    System.out.print("PASS ");
                }
            }
            System.out.println();
        }
    }
}