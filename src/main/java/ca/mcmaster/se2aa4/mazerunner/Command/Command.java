package ca.mcmaster.se2aa4.mazerunner.Command;

/**
 * Interface defining the contract for all maze movement commands.
 * Each command must implement the execute method which performs the movement action.
 * This makes commands first-class citizens that can be stored, passed around, and executed independently.
 */
public interface Command {
    /**
     * Executes the command
     * @return boolean indicating if the command was executed successfully
     */
    boolean execute();
}   