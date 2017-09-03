package model.cell;

/**
 * Created by olivergerhardt on 27.08.17.
 */
public class Cell implements CellRO {

    private boolean isVisited;
    private boolean isMine;
    private boolean isMarkedAsBomb;
    private int neighbourMines;

    public int getNeighbourMines() {
        return neighbourMines;
    }

    public void setNeighbourMines(final int count) {
        if (count >= 0 && count < 9) {
            neighbourMines = count;
        }
    }

    public Cell() {
        isVisited = false;
        isMine = false;
        isMarkedAsBomb = false;
        neighbourMines = 0;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public boolean isMine() {
        return isMine;
    }

    public boolean isMarkedAsBomb() {
        return isMarkedAsBomb;
    }

    public void visit() {
        if (isVisited) {
            throw new IllegalStateException("A cell can only be visited once!");
        }
        isVisited = true;
    }

    public void setMine() {
        isMine = true;
    }

    public void changeMarkedAsBomb() {
        isMarkedAsBomb = !isMarkedAsBomb;
    }

    public void removeMine() {
        isMine = false;
    }
}
