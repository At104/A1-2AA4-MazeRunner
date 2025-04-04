package ca.mcmaster.se2aa4.mazerunner.Command;

import ca.mcmaster.se2aa4.mazerunner.Solver.MazeExplorer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Command class for moving the maze explorer forward.
 * Encapsulates the forward movement action and makes it independent of execution timing.
 */
public class MoveForwardCommand implements Command {
    private final MazeExplorer explorer;
    private static final Logger logger = LogManager.getLogger();
    
    /**
     * Constructor for MoveForwardCommand
     * @param explorer The MazeExplorer instance to control
     */
    public MoveForwardCommand(MazeExplorer explorer) {
        this.explorer = explorer;
    }
    
    /**
     * Executes the forward movement command
     * @return boolean indicating if the movement was successful
     */
    @Override
    public boolean execute() {
        if (!explorer.moveForward()) {
            logger.info("Move forward failed at position: " + explorer.getPosition());
            return false;
        }
        return true;
    }
} 