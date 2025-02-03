package ca.mcmaster.se2aa4.mazerunner.Path;

public class CanonicalPath extends Path {

    public CanonicalPath() {
        super();
    }

    /**
     * Add an instruction to the path
     * @param instruction The instruction to add
     */
    @Override
    public void addInstruction(char instruction) {
        StringBuilder path = getPath();
        if (path.length() == 0) {
            path.append(instruction);
        } 
        else {
            char lastInstruction = path.charAt(path.length() - 1);
            if (lastInstruction == instruction) {
                path.append(instruction);
            }
            else {
                path.append(" " + instruction);
            }
        }
    }
    /**
     * Get the instructions in the path
     * @return {@code String} The instructions in the path
     */
    @Override
    public String getInstructions() {
        return getPath().toString();
    }
    
}