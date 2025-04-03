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
    
    /**
     * Convert a canonical path to a factorized path
     * @param canonicalPath The canonical path to convert
     * @return {@code String} The factorized path
     */
    public static String toFactorizedPath(String canonicalPath) {
        if (canonicalPath == null || canonicalPath.isEmpty()) {
            return "";
        }
        
        // Strip all spaces from the input
        canonicalPath = canonicalPath.replaceAll("\\s+", "");
        
        StringBuilder factorizedPath = new StringBuilder();
        char currentInstruction = canonicalPath.charAt(0);
        int count = 1;
        
        for (int i = 1; i < canonicalPath.length(); i++) {
            char instruction = canonicalPath.charAt(i);
            
            if (instruction == currentInstruction) {
                // Same instruction, increment count
                count++;
            } else {
                // Different instruction, append the current count and instruction
                if (count > 1) {
                    factorizedPath.append(count);
                }
                factorizedPath.append(currentInstruction);
                
                // Start counting the new instruction
                currentInstruction = instruction;
                count = 1;
            }
        }
        
        // Append the last group
        if (count > 1) {
            factorizedPath.append(count);
        }
        factorizedPath.append(currentInstruction);
        
        return factorizedPath.toString();
    }
}