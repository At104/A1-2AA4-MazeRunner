package ca.mcmaster.se2aa4.mazerunner.Maze;

import ca.mcmaster.se2aa4.mazerunner.Position;

public class MazeSolver {
    private Maze maze;
    private MazeExplorer explorer;

    public MazeSolver(Maze maze, MazeExplorer explorer) {
        this.maze = maze;
        this.explorer = explorer;
    }

    public boolean solve() {
        Position endPosition = maze.getEndPosition();
        while (!explorer.getPosition().equals(endPosition)) {
            if (!explorer.moveForward()) {
                explorer.turnRight();
                if (!explorer.moveForward()) {
                    explorer.turnLeft();
                    explorer.turnLeft();
                    if (!explorer.moveForward()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}