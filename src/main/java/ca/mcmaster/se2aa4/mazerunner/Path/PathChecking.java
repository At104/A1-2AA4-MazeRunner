package ca.mcmaster.se2aa4.mazerunner.Path;

import ca.mcmaster.se2aa4.mazerunner.Maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.Solver.MazeExplorer;
import ca.mcmaster.se2aa4.mazerunner.Solver.RightHandSolver;

public class PathChecking {

    /**
     * Verify if the path is valid from the -p flag
     * @param explorer
     * @param path
     * @return {@code boolean} True if the path is valid, false otherwise
     */
    public static boolean verifyPath(MazeExplorer explorer, String path) {
        for (char instruction : path.toCharArray()) {
            switch (instruction) {
                case 'F':
                    if (!explorer.moveForward()) {
                        return false;
                    }
                    break;
                case 'L':
                    explorer.turnLeft();
                    break;
                case 'R':
                    explorer.turnRight();
                    break;
                default:
                    return false;
            }
        }
        return explorer.getPosition().equals(explorer.getMaze().getEndPosition());
    }
    /**
     * Compute the path to solve the maze
     * @param maze
     * @return {@code String} The path to solve the maze
     */
    public static String computePath(Maze maze) {
        MazeExplorer explorer = new MazeExplorer(maze);
        RightHandSolver solver = new RightHandSolver(maze, explorer);

        if (solver.solve()) {
            return explorer.getPathInstructions();
        } 
        else {
            return null;
        }
    }
}