package ca.mcmaster.se2aa4.mazerunner.Path;

// Enum for the directions
public enum Direction {
    UP, RIGHT, DOWN, LEFT;

    /**
     * Turn the direction to the right
     * @return {@code Direction} new direction 
     */
    public Direction turnRight() {
        switch (this) {
            case UP:
                return RIGHT;
            case RIGHT:
                return DOWN;
            case DOWN:
                return LEFT;
            case LEFT:
                return UP;
            default:
                throw new IllegalStateException("Unexpected value: " + this);
        }
    }

    /**
     * Turn the direction to the left
     * @return {@code Direction} new direction 
     */
    public Direction turnLeft() {
        switch (this) {
            case UP:
                return LEFT;
            case LEFT:
                return DOWN;
            case DOWN:
                return RIGHT;
            case RIGHT:
                return UP;
            default:
                throw new IllegalStateException("Unexpected value: " + this);
        }
    }
}