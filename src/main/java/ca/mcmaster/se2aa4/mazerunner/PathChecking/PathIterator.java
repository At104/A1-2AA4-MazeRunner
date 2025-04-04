package ca.mcmaster.se2aa4.mazerunner.PathChecking;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterator for path instructions, works with both canonical and factorized paths
 */
public class PathIterator implements Iterator<Character> {
    private final String path;
    private int currentIndex;
    private int remainingCount;
    private char currentChar;
    private final boolean isFactorized;

    /**
     * Create a new PathIterator for the given path
     * @param path The path to iterate through
     */
    public PathIterator(String path) {
        this.path = path.replaceAll("\\s+", "");
        this.currentIndex = 0;
        this.remainingCount = 0;
        this.currentChar = '\0';
        this.isFactorized = PathChecking.isFactorizedPath(path);
    }

    @Override
    public boolean hasNext() {
        return remainingCount > 0 || currentIndex < path.length();
    }

    @Override
    public Character next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more instructions in path");
        }

        // If we have remaining counts of the current character, return it
        if (remainingCount > 0) {
            remainingCount--;
            return currentChar;
        }

        // Get the next character
        char c = path.charAt(currentIndex++);

        // If we're in a factorized path and the character is a digit
        if (isFactorized && Character.isDigit(c)) {
            int count = Character.getNumericValue(c);

            // Parse multi-digit numbers
            while (currentIndex < path.length() && Character.isDigit(path.charAt(currentIndex))) {
                count = count * 10 + Character.getNumericValue(path.charAt(currentIndex));
                currentIndex++;
            }

            if (currentIndex >= path.length()) {
                throw new IllegalStateException("Invalid factorized path format");
            }

            // The next character after the number is the instruction
            currentChar = path.charAt(currentIndex++);
            remainingCount = count - 1; // -1 because we're returning one now
            return currentChar;
        } else if (c == 'F' || c == 'L' || c == 'R') {
            return c;
        } else {
            // Skip non-instruction characters (like spaces)
            return next();
        }
    }
} 