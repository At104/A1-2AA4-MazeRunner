package ca.mcmaster.se2aa4.mazerunner.PathChecking;

import ca.mcmaster.se2aa4.mazerunner.Maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.Solver.MazeExplorer;

public class CanonicalPathChecking extends PathChecking {

    public CanonicalPathChecking(Maze maze, MazeExplorer explorer) {
        super(maze, explorer);
    }

    @Override
    public boolean verifyPath(String path) {
        MazeExplorer explorer = getExplorer();
        for (char instruction : path.toCharArray()) {
            if (!processInstruction(instruction)) {
                return false;
            }
        }
        boolean result = explorer.getPosition().equals(explorer.getMaze().getEndPosition());
        return result;
    }

    /**
     * Convert a factorized path to a canonical path
     * @param factorizedPath The factorized path to convert
     * @return {@code String} The canonical path
     */
    public static String toCanonicalPath(String factorizedPath) {
        // Strip all spaces from the input
        factorizedPath = factorizedPath.replaceAll("\\s+", "");
        
        StringBuilder canonicalPath = new StringBuilder();
        
        for (int i = 0; i < factorizedPath.length(); i++) {
            char instruction = factorizedPath.charAt(i);
            if (Character.isDigit(instruction)) {
                int count = 0;
                // Parse the entire number
                while (i < factorizedPath.length() && Character.isDigit(factorizedPath.charAt(i))) {
                    count = count * 10 + Character.getNumericValue(factorizedPath.charAt(i));
                    i++;
                }
                if (i >= factorizedPath.length()) {
                    return canonicalPath.toString();
                }
                char nextInstruction = factorizedPath.charAt(i);
                for (int j = 0; j < count; j++) {
                    canonicalPath.append(nextInstruction);
                }
            } else if (instruction == 'F' || instruction == 'L' || instruction == 'R') {
                canonicalPath.append(instruction);
            }
        }
        
        return canonicalPath.toString();
    }
}