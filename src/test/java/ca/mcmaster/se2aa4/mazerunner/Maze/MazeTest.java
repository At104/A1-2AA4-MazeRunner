package ca.mcmaster.se2aa4.mazerunner.Maze;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MazeTest {
    private Maze maze;
    private char[][] sampleMaze = {
        {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
        {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
        {' ', ' ', ' ', ' ', '#', '#', '#', '#', '#', '#'},
        {'#', '#', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
        {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}
    };

    @Test
    void testMazeInitialization() {
        maze = new Maze(sampleMaze);
        assertNotNull(maze);
        assertEquals(new Position(0, 2), maze.getStartPosition());
        assertEquals(new Position(9, 3), maze.getEndPosition());
    }

    @Test
    void testWallDetection() {
        maze = new Maze(sampleMaze);
        assertTrue(maze.isWall(new Position(0, 0)));
        assertFalse(maze.isWall(new Position(0, 2)));
    }

    @Test
    void testGetDimensions() {
        maze = new Maze(sampleMaze);
        Position dimensions = maze.getDimensions();
        assertEquals(10, dimensions.getX()); // width
        assertEquals(6, dimensions.getY()); // height
    }
    
} 