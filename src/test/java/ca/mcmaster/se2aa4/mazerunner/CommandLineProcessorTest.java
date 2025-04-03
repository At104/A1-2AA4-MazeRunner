package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CommandLineProcessorTest {

    @Test
    public void testCommandLineArgs() throws Exception {
        String file = "examples/medium.maz.txt";
        String[] args = { "-i", file, "-p", "path" };
        CommandLineUtils processor = new CommandLineUtils(args);

        assertTrue(processor.hasOption("i"));
        assertEquals(processor.getOptionValue("i"), file);
        assertTrue(processor.hasOption("p"));
        assertEquals(processor.getOptionValue("p"), "path");
    }

    @Test
    public void testCommandLineArgsNoPath() throws Exception {
        String file = "examples/medium.maz.txt";
        String[] args = { "-i", file };
        CommandLineUtils processor = new CommandLineUtils(args);

        assertTrue(processor.hasOption("i"));
        assertEquals(processor.getOptionValue("i"), file);
        assertFalse(processor.hasOption("p"));
    }
} 