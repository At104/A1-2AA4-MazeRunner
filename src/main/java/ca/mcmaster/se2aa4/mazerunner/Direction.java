package ca.mcmaster.se2aa4.mazerunner;

public enum Direction {
    UP, RIGHT, DOWN, LEFT;

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