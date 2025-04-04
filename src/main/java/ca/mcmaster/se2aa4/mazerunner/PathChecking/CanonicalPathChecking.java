package ca.mcmaster.se2aa4.mazerunner.PathChecking;

import ca.mcmaster.se2aa4.mazerunner.Maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.Solver.MazeExplorer;

import java.util.Iterator;

public class CanonicalPathChecking extends PathChecking {

    public CanonicalPathChecking(Maze maze, MazeExplorer explorer) {
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
     * Convert a factorized path to a canonical path
     * @param factorizedPath The factorized path to convert
     * @return {@code String} The canonical path
     */
    public static String toCanonicalPath(String factorizedPath) {
        // Use the PathIterator to convert to canonical form
        StringBuilder canonicalPath = new StringBuilder();
        Iterator<Character> iterator = new PathIterator(factorizedPath);
        
        while (iterator.hasNext()) {
            canonicalPath.append(iterator.next());
        }
        
        return canonicalPath.toString();
    }
}