package ca.mcmaster.se2aa4.mazerunner.Path;

public class FactorizedPath extends Path {
    // Last instruction and its count
    private char lastInstruction = '\0';
    private int count = 0;

    public FactorizedPath() {
        super();
    }

    /**
     * Add an instruction to the factorized path
     * @param instruction The instruction to add
     */
    @Override
    public void addInstruction(char instruction) {
        if (instruction == ' ') return; // Ignore spaces

        if (lastInstruction == '\0') { // First character
            lastInstruction = instruction;
            count = 1;
        } 
        else if (lastInstruction == instruction) {
            count++;
        } 
        else {
            appendCompressed(); // Append previous instruction before switching
            lastInstruction = instruction;
            count = 1;
        }
    }
    /**
     * Get the instructions in the factorized path
     * @return {@code String} The instructions in the factorized path
     */
    @Override
    public String getInstructions() {
        StringBuilder tempPath = new StringBuilder(getPath());
        // Append last instruction
        if (lastInstruction != '\0') {
            if (count > 1) {
                tempPath.append(count);
            }
            tempPath.append(lastInstruction);
        }
        return tempPath.toString().trim();
    }
    /**
     * Helper method to append the last instruction to the path
     */
    private void appendCompressed() {
        if (count > 1) {
            getPath().append(count);
        }
        getPath().append(lastInstruction).append(" ");
    }
}
