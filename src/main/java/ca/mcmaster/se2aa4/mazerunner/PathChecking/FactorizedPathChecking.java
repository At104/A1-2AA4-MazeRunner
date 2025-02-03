package ca.mcmaster.se2aa4.mazerunner.PathChecking;

import ca.mcmaster.se2aa4.mazerunner.Maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.Solver.MazeExplorer;

public class FactorizedPathChecking extends PathChecking {

    public FactorizedPathChecking(Maze maze, MazeExplorer explorer) {
        super(maze, explorer);
    }

    @Override
    public boolean verifyPath(String path) {
        MazeExplorer explorer = getExplorer();
        for (int i = 0; i < path.length(); i++) {
            char instruction = path.charAt(i);
            if (Character.isDigit(instruction)) {
                int count = 0;
                // Parse the entire number
                while (i < path.length() && Character.isDigit(path.charAt(i))) {
                    count = count * 10 + Character.getNumericValue(path.charAt(i));
                    i++;
                }
                if (i >= path.length()) {
                    
                    return false;
                }
                char nextInstruction = path.charAt(i);
                for (int j = 0; j < count; j++) {
                    if (!processInstruction(nextInstruction)) {
                        return false;
                    }
                }
            } 
            else {
                if (!processInstruction(instruction)) {
                    return false;
                }
            }
        }
        boolean result = explorer.getPosition().equals(explorer.getMaze().getEndPosition());
        return result;
    }
}