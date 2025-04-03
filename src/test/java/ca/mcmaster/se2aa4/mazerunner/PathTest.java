package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.Maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.Maze.MazeInitializer;
import ca.mcmaster.se2aa4.mazerunner.Solver.MazeExplorer;
import ca.mcmaster.se2aa4.mazerunner.PathChecking.CanonicalPathChecking;
import ca.mcmaster.se2aa4.mazerunner.PathChecking.FactorizedPathChecking;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

class PathTest {
    private Maze maze;
    private MazeInitializer mazeInitializer = new MazeInitializer();
    private MazeExplorer explorer;
    private static final Logger logger = LogManager.getLogger();
  
    @BeforeEach
    void setUp() throws IOException {
        maze = mazeInitializer.initializeMaze("examples/medium.maz.txt");
        explorer = new MazeExplorer(maze);
    }

    @Test
    public void testPathEquivalence() throws Exception {
        File directory = new File("examples");
        File[] mazes = directory.listFiles();

        for (File mazeFile : mazes) {
            Maze maze = mazeInitializer.initializeMaze(mazeFile.getAbsolutePath());
            explorer = new MazeExplorer(maze);
            String factoredPath = explorer.getPathInstructions();
            logger.info("Maze Dimensions: {}\n", maze.getDimensions());

            String canonicalPath = CanonicalPathChecking.toCanonicalPath(factoredPath);
            logger.info("Canonical Path: {}", canonicalPath);
            String newFactoredPath = FactorizedPathChecking.toFactorizedPath(canonicalPath);
            logger.info("New Factored Path: {}", newFactoredPath);
            assertEquals(factoredPath, newFactoredPath);
        }
    }
    
    @Test
    void testCanonicalPathConversion() {
        // Test simple conversions
        assertEquals("FFFRR", CanonicalPathChecking.toCanonicalPath("3F2R"));
        assertEquals("R", CanonicalPathChecking.toCanonicalPath("R"));
        assertEquals("FFFFFF", CanonicalPathChecking.toCanonicalPath("6F"));
        assertEquals("FFLFF", CanonicalPathChecking.toCanonicalPath("2FL2F"));
        
        // Test with spaces
        assertEquals("FFFRR", CanonicalPathChecking.toCanonicalPath("3F 2R"));
        assertEquals("FFLFF", CanonicalPathChecking.toCanonicalPath("2F L 2F"));
        
        // Test with large numbers
        String largePath = "100F";
        String canonicalLargePath = CanonicalPathChecking.toCanonicalPath(largePath);
        assertEquals(100, canonicalLargePath.length());
        assertTrue(canonicalLargePath.matches("^F+$"));
    }
    
    @Test
    void testFactorizedPathConversion() {
        // Test simple conversions
        assertEquals("3F2R", FactorizedPathChecking.toFactorizedPath("FFFRR"));
        assertEquals("R", FactorizedPathChecking.toFactorizedPath("R"));
        assertEquals("6F", FactorizedPathChecking.toFactorizedPath("FFFFFF"));
        assertEquals("2FL2F", FactorizedPathChecking.toFactorizedPath("FFLFF"));
        
        // Test with spaces
        assertEquals("3F2R", FactorizedPathChecking.toFactorizedPath("FFF RR"));
        assertEquals("2FL2F", FactorizedPathChecking.toFactorizedPath("FF L FF"));
        
        // Test with large sequences
        StringBuilder largePath = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            largePath.append("F");
        }
        assertEquals("100F", FactorizedPathChecking.toFactorizedPath(largePath.toString()));
    }
} 