package ca.mcmaster.se2aa4.mazerunner.Path;

public class FactorizedPath extends Path {
    private char lastInstruction = '\0';
    private int count = 0;

    //TODO: Check whether it works after full navigation is implemented
    public FactorizedPath() {
        super();
    }

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

    @Override
    public String getInstructions() {
        StringBuilder tempPath = new StringBuilder(path);
        if (lastInstruction != '\0') {
            if (count > 1) {
                tempPath.append(count);
            }
            tempPath.append(lastInstruction);
        }
        return tempPath.toString().trim();
    }

    private void appendCompressed() {
        if (count > 1) {
            path.append(count);
        }
        path.append(lastInstruction).append(" ");
    }
}
