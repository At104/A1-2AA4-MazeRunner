package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.Path.Direction;

public class DirectionTest {

    @Test
    public void testUp() {
        Direction direction = Direction.UP;
        assertEquals(Direction.LEFT, direction.turnLeft());
        assertEquals(Direction.RIGHT, direction.turnRight());
    }

    @Test
    public void testLeft() {
        Direction direction = Direction.LEFT;
        assertEquals(Direction.DOWN, direction.turnLeft());
        assertEquals(Direction.UP, direction.turnRight());
    }

    @Test
    public void testDown() {
        Direction direction = Direction.DOWN;
        assertEquals(Direction.RIGHT, direction.turnLeft());
        assertEquals(Direction.LEFT, direction.turnRight());
    }

    @Test
    public void testRight() {
        Direction direction = Direction.RIGHT;
        assertEquals(Direction.UP, direction.turnLeft());
        assertEquals(Direction.DOWN, direction.turnRight());
    }
} 