package ca.mcmaster.se2aa4.mazerunner.Solver;

import ca.mcmaster.se2aa4.mazerunner.Maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.Maze.Position;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RightHandSolver implements Solver {
    private Maze maze;
    private MazeExplorer explorer;
    private Logger logger = LogManager.getLogger();

    public RightHandSolver(Maze maze, MazeExplorer explorer) {
        this.maze = maze;
        this.explorer = explorer;
    }


    public String getPath() {
        return explorer.getPath();
    }

    @Override
    public boolean solve() {
        Position endPosition = maze.getEndPosition();
        logger.info("Start position: " + explorer.getPosition());
        logger.info("End position: " + endPosition);

        while (!explorer.getPosition().equals(endPosition)) {
            logger.info("Current position: " + explorer.getPosition());
            logger.info("Current direction: " + explorer.getDirection());

            // Check if the cells around are walls
            Position leftPosition = explorer.getPosition().move(explorer.getDirection().turnLeft());
            Position rightPosition = explorer.getPosition().move(explorer.getDirection().turnRight());
            Position forwardPosition = explorer.getPosition().move(explorer.getDirection());

            if (!maze.isWall(rightPosition)) {
                explorer.turnRight();      
            }
            else if (maze.isWall(forwardPosition)) {
                if (maze.isWall(leftPosition)) {
                    explorer.turnRight();
                    explorer.turnRight();
                }
                else {
                    explorer.turnLeft();
                }
            }
            explorer.moveForward();
                
        }

        logger.info("End position reached: " + explorer.getPosition());
        return true;
    }
}