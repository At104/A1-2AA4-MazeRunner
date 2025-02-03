package ca.mcmaster.se2aa4.mazerunner.PathChecking;

import ca.mcmaster.se2aa4.mazerunner.Maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.Solver.MazeExplorer;
import ca.mcmaster.se2aa4.mazerunner.Solver.RightHandSolver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class PathChecking {
    private static final Logger logger = LogManager.getLogger();
    private MazeExplorer explorer;
    private Maze maze;

    public PathChecking(Maze maze, MazeExplorer explorer) {
        this.maze = maze;
        this.explorer = explorer;
    }

    protected Maze getMaze() {
        return maze;
    }

    protected MazeExplorer getExplorer() {
        return explorer;
    }

    /**
     * Verify if the path is valid
     * @param path
     * @return {@code boolean} True if the path is valid, false otherwise
     */
    public abstract boolean verifyPath(String path);

    /**
     * Compute the path to solve the maze
     * @return {@code String} The path to solve the maze
     */
    public String computePath() {
        RightHandSolver solver = new RightHandSolver(maze, explorer);

        if (solver.solve()) {
            return explorer.getPathInstructions();
        } 
        else {
            return null;
        }
    }

    /**
     * Check if a path is factorized
     * @param path
     * @return {@code boolean} True if the path is factorized, false otherwise
     */
    public static boolean isFactorizedPath(String path) {
        return path.matches(".*\\d.*");
    }

    /**
     * Process a single instruction
     * @param instruction
     * @return {@code boolean} True if the instruction was processed successfully, false otherwise
     */
    protected boolean processInstruction(char instruction) {
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
        return true;
    }
}