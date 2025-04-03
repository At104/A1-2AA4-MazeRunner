package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.Solver.RightHandSolver;
import ca.mcmaster.se2aa4.mazerunner.Maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.Maze.MazeInitializer;
import ca.mcmaster.se2aa4.mazerunner.Maze.Position;
import ca.mcmaster.se2aa4.mazerunner.Solver.MazeExplorer;
import ca.mcmaster.se2aa4.mazerunner.Solver.Solver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

class MazeTest {
    private Maze maze;
    private Solver solver;
    private MazeInitializer mazeInitializer = new MazeInitializer();
    private MazeExplorer explorer;
    private static final Logger logger = LogManager.getLogger();
  
    @BeforeEach
    void setUp() throws IOException {
        maze = mazeInitializer.initializeMaze("examples/medium.maz.txt");
        explorer = new MazeExplorer(maze);
        solver = new RightHandSolver(maze, explorer);
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
} 