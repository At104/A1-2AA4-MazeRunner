package ca.mcmaster.se2aa4.mazerunner.Command;

import ca.mcmaster.se2aa4.mazerunner.Solver.MazeExplorer;

/**
 * Command class for turning the maze explorer right.
 * Encapsulates the right turn action and makes it independent of execution timing.
 */
public class TurnRightCommand implements Command {
    private final MazeExplorer explorer;
    
    /**
     * Constructor for TurnRightCommand
     * @param explorer The MazeExplorer instance to control
     */
    public TurnRightCommand(MazeExplorer explorer) {
        this.explorer = explorer;
    }
    
    /**
     * Executes the right turn command
     * @return boolean indicating if the turn was successful (always true for turns)
     */
    @Override
    public boolean execute() {
        explorer.turnRight();
        return true;
    }
} 