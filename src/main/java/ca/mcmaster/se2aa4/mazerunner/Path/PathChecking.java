package ca.mcmaster.se2aa4.mazerunner.Path;

import ca.mcmaster.se2aa4.mazerunner.Maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.Solver.MazeExplorer;
import ca.mcmaster.se2aa4.mazerunner.Solver.RightHandSolver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PathChecking {
    private static final Logger logger = LogManager.getLogger();

    /**
     * Verify if the canonical path is valid from the -p flag
     * @param explorer
     * @param path
     * @return {@code boolean} True if the path is valid, false otherwise
     */
    public boolean verifyCanonicalPath(MazeExplorer explorer, String path) {
        for (char instruction : path.toCharArray()) {
            logger.info("Processing instruction: " + instruction);
            switch (instruction) {
                case 'F':
                    if (!explorer.moveForward()) {
                        logger.info("Move forward failed at position: " + explorer.getPosition());
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
                    logger.info("Invalid instruction: " + instruction);
                    return false;
            }
        }
        boolean result = explorer.getPosition().equals(explorer.getMaze().getEndPosition());
        logger.info("Verification result: " + result + " at position: " + explorer.getPosition());
        return result;
    }

    /**
     * Verify if the factorized path is valid from the -p flag
     * @param explorer
     * @param path
     * @return {@code boolean} True if the path is valid, false otherwise
     */
    public boolean verifyFactorizedPath(MazeExplorer explorer, String path) {
        String expandedPath = expandFactorizedPath(path);
        logger.info("Expanded factorized path: " + expandedPath);
        return verifyCanonicalPath(explorer, expandedPath);
    }

    /**
     * Expand a factorized path to its canonical form
     * @param path
     * @return {@code String} The expanded canonical path
     */
    private static String expandFactorizedPath(String path) {
        StringBuilder expandedPath = new StringBuilder();
        int i = 0;
        while (i < path.length()) {
            char instruction = path.charAt(i);
            if (Character.isDigit(instruction)) {
                int count = 0;
                while (i < path.length() && Character.isDigit(path.charAt(i))) {
                    count = count * 10 + Character.getNumericValue(path.charAt(i));
                    i++;
                }
                char nextInstruction = path.charAt(i);
                for (int j = 0; j < count; j++) {
                    expandedPath.append(nextInstruction);
                }
                i++;
            } else {
                expandedPath.append(instruction);
                i++;
            }
        }
        logger.info("Expanded path: " + expandedPath.toString());
        return expandedPath.toString();
    }

    /**
     * Check if a path is factorized
     * @param path
     * @return {@code boolean} True if the path is factorized, false otherwise
     */
    public boolean isFactorizedPath(String path) {
        return path.matches(".*\\d.*");
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
        } else {
            return "No path found";
        }
    }
}