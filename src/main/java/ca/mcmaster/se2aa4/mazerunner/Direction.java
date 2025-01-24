package ca.mcmaster.se2aa4.mazerunner;

public class Direction {
    private DirectionType direction;

    public Direction(DirectionType initialDirection) {
        this.direction = initialDirection;
    }

    public DirectionType getDirection() {
        return direction;
    }

    public void turnRight() {
        switch (direction) {
            case UP:
                direction = DirectionType.RIGHT;
                break;
            case RIGHT:
                direction = DirectionType.DOWN;
                break;
            case DOWN:
                direction = DirectionType.LEFT;
                break;
            case LEFT:
                direction = DirectionType.UP;
                break;
        }
    }

    public void turnLeft() {
        switch (direction) {
            case UP:
                direction = DirectionType.LEFT;
                break;
            case LEFT:
                direction = DirectionType.DOWN;
                break;
            case DOWN:
                direction = DirectionType.RIGHT;
                break;
            case RIGHT:
                direction = DirectionType.UP;
                break;
        }
    }

    public enum DirectionType {
        UP, RIGHT, DOWN, LEFT
    }
}