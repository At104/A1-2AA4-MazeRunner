package ca.mcmaster.se2aa4.mazerunner.Path;


public class Path {
    protected StringBuilder path;

    public Path() {
        this.path = new StringBuilder();
    }

    public Path(String initialPath) {
        this.path = new StringBuilder(initialPath);
    }

    public void addInstruction(char instruction) {
        this.path.append(instruction);
    }

    public String getInstructions() {
        return this.path.toString();
    }


}