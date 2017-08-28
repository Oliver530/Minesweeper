package minesweeper4java;

/**
 * Created by olivergerhardt on 27.08.17.
 */
public class Cell {

    private boolean isVisited;
    private boolean isMine;

    public Cell() {
        this.isVisited = false;
        this.isMine = false;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public boolean isMine() {
        return isMine;
    }

    public void visit() {
        if (isVisited) {
            throw new IllegalStateException("A cell can only be visited once!");
        }
        isVisited = true;
    }

    public boolean setMine() {
        if (isMine) {
            return false;
        }
        isMine = true;
        return true;
    }

}
