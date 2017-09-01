package model.cell;

/**
 * Created by olivergerhardt on 27.08.17.
 */
public class Cell implements CellRO {

    private boolean isVisited;
    private boolean isMine;
    private boolean markedAsBomb;
    private int countOfNeighbourMines;
    
    public int getCountOfNeighbourMines() {
        return countOfNeighbourMines;
    }

    public void setCountOfNeighbourMines(int countOfNeighbourMines) {
        this.countOfNeighbourMines = countOfNeighbourMines;
    }

    public Cell() {
        this.isVisited = false;
        this.isMine = false;
        this.markedAsBomb = false;
        countOfNeighbourMines = 0;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public boolean isMine() {
        return isMine;
    }

    public boolean isMarkedAsBomb() {
        return this.markedAsBomb;
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

    public void changeMarkedAsBomb() {
        this.markedAsBomb = !this.markedAsBomb;
    }
}
