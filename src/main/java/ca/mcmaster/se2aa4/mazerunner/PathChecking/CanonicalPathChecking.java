package ca.mcmaster.se2aa4.mazerunner.PathChecking;

import ca.mcmaster.se2aa4.mazerunner.Maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.Solver.MazeExplorer;

public class CanonicalPathChecking extends PathChecking {

    public CanonicalPathChecking(Maze maze, MazeExplorer explorer) {
        super(maze, explorer);
    }

    @Override
    public boolean verifyPath(String path) {
        MazeExplorer explorer = getExplorer();
        for (char instruction : path.toCharArray()) {
            if (!processInstruction(instruction)) {
                return false;
            }
        }
        boolean result = explorer.getPosition().equals(explorer.getMaze().getEndPosition());
        return result;
    }
}