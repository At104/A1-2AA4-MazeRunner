package ca.mcmaster.se2aa4.mazerunner;

public class Maze {

    private Cell[][] cells;

    public Maze(char[][] readMaze) {
        cells = new Cell[readMaze.length][readMaze[0].length];
        for (int i = 0; i < readMaze.length; i++) {
            for (int j = 0; j < readMaze[0].length; j++) {
                if (readMaze[i][j] == '#') {
                    cells[i][j] = new Cell(true); // Wall is true
                } 
                else if (readMaze[i][j] == ' ') {
                    cells[i][j] = new Cell(false); // Passage is false
                }
            }
        }
    }


    public Maze(Cell[][] cells) {
        this.cells = cells;
    }

    public Cell getCell(Position position) {
        return cells[position.getX()][position.getY()];
    }

    public Position getDimensions() {
        return new Position(cells.length, cells[0].length);
    }

    public boolean isWall(Position position) {
        return getCell(position).isWall();
    }

    public Position getStartPosition() {
        for (int i = 0; i < cells.length; i++) {
            if (!isWall(new Position(0, i))) {
                return new Position(0, i);
            }
        }
        return null; // No start position
    }

    public Position getEndPosition() {
        for (int i = 0; i < cells.length; i++) {
            if (!isWall(new Position(cells.length - 1, i))) {
                return new Position(cells.length - 1, i);
            }
        }
        return null; // No end position
        
    }


}