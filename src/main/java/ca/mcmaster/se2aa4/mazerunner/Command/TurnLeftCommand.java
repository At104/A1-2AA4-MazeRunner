package ca.mcmaster.se2aa4.mazerunner.Command;

import ca.mcmaster.se2aa4.mazerunner.Solver.MazeExplorer;

/**
 * Command class for turning the maze explorer left.
 * Encapsulates the left turn action and makes it independent of execution timing.
 */
public class TurnLeftCommand implements Command {
    private final MazeExplorer explorer;
    
    /**
     * Constructor for TurnLeftCommand
     * @param explorer The MazeExplorer instance to control
     */
    public TurnLeftCommand(MazeExplorer explorer) {
        this.explorer = explorer;
    }
    
    /**
     * Executes the left turn command
     * @return boolean indicating if the turn was successful (always true for turns)
     */
    @Override
    public boolean execute() {
        explorer.turnLeft();
        return true;
    }
} 