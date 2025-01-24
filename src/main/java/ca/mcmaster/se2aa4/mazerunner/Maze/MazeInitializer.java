package ca.mcmaster.se2aa4.mazerunner.Maze;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MazeInitializer {
    public static Maze initializeMaze(String filePath) throws IOException {
        List<char[]> mazeDataList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                mazeDataList.add(line.toCharArray());
            }
        }

        if (mazeDataList.isEmpty()) {
            throw new IllegalArgumentException("Maze file is empty.");
        }

        char[][] mazeData = mazeDataList.toArray(new char[0][]);
        return new Maze(mazeData);
    }
}