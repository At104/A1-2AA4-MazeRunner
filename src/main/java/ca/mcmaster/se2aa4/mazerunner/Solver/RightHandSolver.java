package ca.mcmaster.se2aa4.mazerunner.Solver;

import ca.mcmaster.se2aa4.mazerunner.Maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.Maze.Position;
import ca.mcmaster.se2aa4.mazerunner.Command.MoveForwardCommand;
import ca.mcmaster.se2aa4.mazerunner.Command.TurnLeftCommand;
import ca.mcmaster.se2aa4.mazerunner.Command.TurnRightCommand;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RightHandSolver implements Solver {
    private static final Logger logger = LogManager.getLogger();
    private final Maze maze;
    private final MazeExplorer explorer;

    public RightHandSolver(Maze maze, MazeExplorer explorer) {
        this.maze = maze;
        this.explorer = explorer;
    }

    @Override
    public boolean solve() {
        Position endPosition = maze.getEndPosition();
        logger.info("Start position: " + explorer.getPosition());
        logger.info("End position: " + endPosition);

        while (!explorer.getPosition().equals(endPosition)) {
            Position currentPos = explorer.getPosition();
            Position rightPos = currentPos.move(explorer.getDirection().turnRight());
            Position forwardPos = currentPos.move(explorer.getDirection());
            Position leftPos = currentPos.move(explorer.getDirection().turnLeft());

            boolean rightWall = maze.isWall(rightPos);
            boolean forwardWall = maze.isWall(forwardPos);
            boolean leftWall = maze.isWall(leftPos);

            if (!rightWall) {
                new TurnRightCommand(explorer).execute();
            } 
            else if (forwardWall) {
                if (leftWall) {
                    new TurnRightCommand(explorer).execute();
                    new TurnRightCommand(explorer).execute();
                } 
                else {
                    new TurnLeftCommand(explorer).execute();
                }
            }
            // Move forward after turning
            new MoveForwardCommand(explorer).execute();
        }

        logger.info("End position reached: " + explorer.getPosition());
        return true;
    }
}