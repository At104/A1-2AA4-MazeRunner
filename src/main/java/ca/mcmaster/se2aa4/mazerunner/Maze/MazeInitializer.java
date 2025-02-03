package ca.mcmaster.se2aa4.mazerunner.Maze;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MazeInitializer {
    
    public Maze initializeMaze(String filePath) throws IOException {
        // Read maze from file
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        char[][] mazeData = reader
                // Read all lines from the file
                .lines()
                // Convert each line to a character array
                .map(String::toCharArray)
                // Then collect all the character arrays into a list of character arrays
                .toArray(char[][]::new);

        reader.close();
        // Create a new maze object with the maze data and return it
        char[][] maze = mazeData;
        return new Maze(maze);
    }
}