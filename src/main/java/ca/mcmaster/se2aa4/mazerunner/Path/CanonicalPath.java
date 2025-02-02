package ca.mcmaster.se2aa4.mazerunner.Path;

public class CanonicalPath extends Path {

    public CanonicalPath() {
        super();
    }

    @Override
    public void addInstruction(char instruction) {
        if (this.path.length() == 0) {
            this.path.append(instruction);
        } 
        else {
            char lastInstruction = this.path.charAt(this.path.length() - 1);
            if (lastInstruction == instruction) {
                this.path.append(instruction);
            }
            else {
                this.path.append(" " + instruction);
            }
        }
    }

    @Override
    public String getInstructions() {
        return this.path.toString();
    }
    
}