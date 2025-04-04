package ca.mcmaster.se2aa4.mazerunner.PathChecking;

import ca.mcmaster.se2aa4.mazerunner.Maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.Solver.MazeExplorer;
import ca.mcmaster.se2aa4.mazerunner.Solver.RightHandSolver;
import ca.mcmaster.se2aa4.mazerunner.Solver.Solver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class PathChecking {
    private static final Logger logger = LogManager.getLogger();
    private MazeExplorer explorer;
    private Maze maze;

    /**
     * Command interface defining the contract for all maze movement commands
     */
    private interface Command {
        /**
         * Executes the command
         * @return boolean indicating if the command was executed successfully
         */
        boolean execute();
    }

    /**
     * Command for moving forward in the maze
     */
    private class MoveForwardCommand implements Command {
        @Override
        public boolean execute() {
            if (!explorer.moveForward()) {
                logger.info("Move forward failed at position: " + explorer.getPosition());
                return false;
            }
            return true;
        }
    }

    /**
     * Command for turning left in the maze
     */
    private class TurnLeftCommand implements Command {
        @Override
        public boolean execute() {
            explorer.turnLeft();
            return true;
        }
    }

    /**
     * Command for turning right in the maze
     */
    private class TurnRightCommand implements Command {
        @Override
        public boolean execute() {
            explorer.turnRight();
            return true;
        }
    }

    public PathChecking(Maze maze, MazeExplorer explorer) {
        this.maze = maze;
        this.explorer = explorer;
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
        Solver solver = new RightHandSolver(maze, explorer);

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
     * Process a single instruction using the Command pattern
     * @param instruction The instruction to process
     * @return {@code boolean} True if the instruction was processed successfully, false otherwise
     */
    protected boolean processInstruction(char instruction) {
        logger.info("Processing instruction: " + instruction);
        Command command;
        
        switch (instruction) {
            case 'F':
                command = new MoveForwardCommand();
                break;
            case 'L':
                command = new TurnLeftCommand();
                break;
            case 'R':
                command = new TurnRightCommand();
                break;
            default:
                logger.info("Invalid instruction: " + instruction);
                return false;
        }
        
        return command.execute();
    }
}