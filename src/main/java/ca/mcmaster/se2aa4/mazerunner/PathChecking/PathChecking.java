package ca.mcmaster.se2aa4.mazerunner.PathChecking;

import ca.mcmaster.se2aa4.mazerunner.Command.Command;
import ca.mcmaster.se2aa4.mazerunner.Command.MoveForwardCommand;
import ca.mcmaster.se2aa4.mazerunner.Command.TurnLeftCommand;
import ca.mcmaster.se2aa4.mazerunner.Command.TurnRightCommand;
import ca.mcmaster.se2aa4.mazerunner.Maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.Solver.MazeExplorer;
import ca.mcmaster.se2aa4.mazerunner.Solver.RightHandSolver;
import ca.mcmaster.se2aa4.mazerunner.Solver.Solver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;

public abstract class PathChecking {
    private static final Logger logger = LogManager.getLogger();
    private MazeExplorer explorer;
    private Maze maze;

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
                command = new MoveForwardCommand(explorer);
                break;
            case 'L':
                command = new TurnLeftCommand(explorer);
                break;
            case 'R':
                command = new TurnRightCommand(explorer);
                break;
            default:
                logger.info("Invalid instruction: " + instruction);
                return false;
        }
        
        return command.execute();
    }
    
    /**
     * Get an iterator for the given path string
     * @param path The path string to iterate through
     * @return {@code Iterator<Character>} An iterator for the path
     */
    protected Iterator<Character> getPathIterator(String path) {
        return new PathIterator(path);
    }
}