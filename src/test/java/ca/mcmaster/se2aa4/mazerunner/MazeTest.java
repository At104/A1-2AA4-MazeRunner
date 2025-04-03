package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.Maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.Maze.MazeInitializer;
import ca.mcmaster.se2aa4.mazerunner.Maze.Position;
import ca.mcmaster.se2aa4.mazerunner.Maze.Cell;
import ca.mcmaster.se2aa4.mazerunner.Solver.MazeExplorer;
import ca.mcmaster.se2aa4.mazerunner.Solver.RightHandSolver;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

class MazeTest {
    private Maze maze;
    private MazeInitializer mazeInitializer = new MazeInitializer();
  
    @BeforeEach
    void setUp() throws IOException {
        maze = mazeInitializer.initializeMaze("examples/medium.maz.txt");
    }

    @Test
    void testCellTypes() {
        // Test the WALL cell type
        Cell wallCell = Cell.WALL;
        assertEquals(Cell.WALL, wallCell);
        
        // Test the PASS cell type
        Cell passCell = Cell.PASS;
        assertEquals(Cell.PASS, passCell);
    }

    @Test
    void testMazeInitialization() {
        assertNotNull(maze);
        assertEquals(new Position(0, 23), maze.getStartPosition());
        assertEquals(new Position(30, 27), maze.getEndPosition());
    }

    @Test
    void testWallDetection() {
        assertTrue(maze.isWall(new Position(0, 0)));
        assertFalse(maze.isWall(new Position(0, 23))); // Start position
    }

    @Test
    void testGetDimensions() {
        Position dimensions = maze.getDimensions();
        assertEquals(31, dimensions.getX()); // Columns
        assertEquals(31, dimensions.getY()); // Rows
    }

    @Test
    void testMazeSolver() {
        MazeExplorer explorer = new MazeExplorer(maze);
        RightHandSolver solver = new RightHandSolver(maze, explorer);
        assertTrue(solver.solve());
    }
    
} 