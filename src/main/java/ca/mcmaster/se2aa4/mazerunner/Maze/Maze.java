package ca.mcmaster.se2aa4.mazerunner.Maze;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Iterator;

public class Maze {

    //Initialize logger and variables
    private static final Logger logger = LogManager.getLogger();
    private Position startPosition;
    private Position endPosition;
    private Cell[][] maze;

    /**
     * Constructor for the Maze class.
     * @param mazeData {@code Maze} The maze data as a 2D char array
     */
    public Maze(char[][] mazeData) {
        int rows = mazeData.length;
        int cols = mazeData[0].length;
        this.maze = new Cell[rows][cols];

        logger.info("Initializing maze with dimensions: {}x{}", rows, cols);
        // Initialize the maze with the data from the file
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

    
    
    /**
     * Get a column iterator for the maze
     * @return Iterator<Cell> that traverses the maze column by column
     */
    public Iterator<Cell> getColumnIterator() {
        return new ColumnIterator(maze);
    }

    

    /**
     * Find the start position of the maze.
     * @return {@code Position} The start position
     */
    private Position findStartPosition() {
        Iterator<Cell> colIterator = getColumnIterator();
        int row = 0;
        while (colIterator.hasNext()) {
            Cell cell = colIterator.next();
            if (cell == Cell.PASS) {
                logger.info("Found start position at: (0, {})", row);
                return new Position(0, row);
            }
            row++;
            if (row >= maze.length) {
                row = 0;
            }
        }
        throw new IllegalArgumentException("No start position found in the first column.");
    }

    /**
     * Find the end position of the maze.
     * @return {@code Position} The end position
     */
    private Position findEndPosition() {
        int lastCol = maze[0].length - 1;
        Iterator<Cell> colIterator = getColumnIterator();
        int currentCol = 0;
        int row = 0;
        
        // Skip to the last column
        while (currentCol < lastCol && colIterator.hasNext()) {
            colIterator.next();
            row++;
            if (row >= maze.length) {
                row = 0;
                currentCol++;
            }
        }
        
        // Check the last column
        while (colIterator.hasNext()) {
            Cell cell = colIterator.next();
            if (cell == Cell.PASS) {
                logger.info("Found end position at: ({}, {})", lastCol, row);
                return new Position(lastCol, row);
            }
            row++;
        }
        
        throw new IllegalArgumentException("No end position found in the last column.");
    }

    /**
     * Get the cell at a given position.
     * @param position
     * @return {@code Cell} The cell
     */
    public Cell getCell(Position position) {
        return this.maze[position.getY()][position.getX()];
    }

    /**
     * Check if a given position is a wall.
     * @param position
     * @return {@code boolean }True if the position is a wall, false otherwise.
     */
    public boolean isWall(Position position) {
        return getCell(position) == Cell.WALL;
    }

    /**
     * Get the start position of the maze.
     * @return {@code Position} Start position
     */
    public Position getStartPosition() {
        return startPosition;
    }

    /**
     * Get the end position of the maze.
     * @return {@code Position} End position 
     */
    public Position getEndPosition() {
        return endPosition;
    }

    /**
     * Get the dimensions of the maze.
     * @return {@code Position} The dimensions as a Position
     */
    public Position getDimensions() {
        return new Position(this.maze[0].length,this.maze.length);
    }


    /**
     * Print the maze to the console.
     
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
    */
    
}