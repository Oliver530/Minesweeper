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

    public void setCountOfNeighbourMines(final int count) {
        countOfNeighbourMines = count;
    }

    public Cell() {
        isVisited = false;
        isMine = false;
        markedAsBomb = false;
        countOfNeighbourMines = 0;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public boolean isMine() {
        return isMine;
    }

    public boolean isMarkedAsBomb() {
        return markedAsBomb;
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
        markedAsBomb = !markedAsBomb;
    }

    public void removeMine() {
        isMine = false;
    }
}
