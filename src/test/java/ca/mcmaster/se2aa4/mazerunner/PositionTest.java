package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.Maze.Position;
import ca.mcmaster.se2aa4.mazerunner.Path.Direction;

public class PositionTest {
    private Position position;

    @BeforeEach
    public void initialize() {
        position = new Position(0, 0);
    }

    @Test
    public void testPositionInitialization() {
        assertEquals(0, position.getX());
        assertEquals(0, position.getY());
    }

    @Test
    public void testSetPosition() {
        position.setX(5);
        position.setY(10);
        assertEquals(5, position.getX());
        assertEquals(10, position.getY());
    }

    @Test
    public void testMoveLeft() {
        position = position.move(Direction.LEFT);
        assertEquals(-1, position.getX());
        assertEquals(0, position.getY());
    }

    @Test
    public void testMoveRight() {
        position = position.move(Direction.RIGHT);
        assertEquals(1, position.getX());
        assertEquals(0, position.getY());
    }

    @Test
    public void testMoveUp() {
        position = position.move(Direction.UP);
        assertEquals(0, position.getX());
        assertEquals(-1, position.getY());
    }

    @Test
    public void testMoveDown() {
        position = position.move(Direction.DOWN);
        assertEquals(0, position.getX());
        assertEquals(1, position.getY());
    }
} 