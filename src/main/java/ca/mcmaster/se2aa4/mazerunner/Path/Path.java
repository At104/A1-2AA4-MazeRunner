package ca.mcmaster.se2aa4.mazerunner.Path;

import ca.mcmaster.se2aa4.mazerunner.Solver.MazeExplorer;

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

    public boolean verifyPath(MazeExplorer explorer) {
        for (char instruction : path.toString().toCharArray()) {
            switch (instruction) {
                case 'F':
                    if (!explorer.moveForward()) {
                        return false;
                    }
                    break;
                case 'L':
                    explorer.turnLeft();
                    break;
                case 'R':
                    explorer.turnRight();
                    break;
                default:
                    return false;
            }
        }
        return explorer.getPosition().equals(explorer.getMaze().getEndPosition());
    }


}