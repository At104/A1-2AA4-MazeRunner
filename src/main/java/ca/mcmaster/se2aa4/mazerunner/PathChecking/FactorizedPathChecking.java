package ca.mcmaster.se2aa4.mazerunner.PathChecking;

import ca.mcmaster.se2aa4.mazerunner.Maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.Solver.MazeExplorer;

import java.util.Iterator;

public class FactorizedPathChecking extends PathChecking {

    public FactorizedPathChecking(Maze maze, MazeExplorer explorer) {
        super(maze, explorer);
    }

    @Override
    public boolean verifyPath(String path) {
        MazeExplorer explorer = getExplorer();
        
        // Use the iterator to process each instruction
        Iterator<Character> iterator = getPathIterator(path);
        while (iterator.hasNext()) {
            char instruction = iterator.next();
            if (!processInstruction(instruction)) {
                return false;
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
        
        // Iterate through the canonical path and count consecutive instructions
        StringBuilder factorizedPath = new StringBuilder();
        Iterator<Character> iterator = new PathIterator(canonicalPath);
        
        if (!iterator.hasNext()) {
            return "";
        }
        
        char currentInstruction = iterator.next();
        int count = 1;
        
        while (iterator.hasNext()) {
            char instruction = iterator.next();
            
            if (instruction == currentInstruction) {
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