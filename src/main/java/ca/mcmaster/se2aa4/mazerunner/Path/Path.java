package ca.mcmaster.se2aa4.mazerunner.Path;


public abstract class Path {
    protected StringBuilder path;

    public Path() {
        this.path = new StringBuilder();
    }

    public abstract void addInstruction(char instruction);
    

    public abstract String getInstructions();


}