package ca.mcmaster.se2aa4.mazerunner;

public class Path {
    private StringBuilder path;

    public Path() {
        this.path = new StringBuilder();
    }

    public void addInstruction(char instruction) {
        this.path.append(instruction);
    }

    public String getInstructions() {
        return this.path.toString();
    }

}