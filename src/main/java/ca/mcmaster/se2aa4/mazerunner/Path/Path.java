package ca.mcmaster.se2aa4.mazerunner.Path;


public abstract class Path {
    private StringBuilder path;

    public Path() {
        this.path = new StringBuilder();
    }
    /**
     * Get the path
     * @return {@code StringBuilder} The path
     */
    protected StringBuilder getPath() {
        return this.path;
    }

    public abstract void addInstruction(char instruction);
    

    public abstract String getInstructions();


}