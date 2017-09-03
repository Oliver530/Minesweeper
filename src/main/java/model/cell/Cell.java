package model.cell;

/**
 * Created by olivergerhardt on 27.08.17.
 */
public class Cell implements CellRO {

    private boolean isVisited;
    private boolean isMine;
    private boolean isMarkedAsBomb;


    public Cell() {
        isVisited = false;
        isMine = false;
        isMarkedAsBomb = false;
    }


    @Override
    public boolean isVisited() {
        return isVisited;
    }

    @Override
    public boolean isMine() {
        return isMine;
    }

    @Override
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

    public void removeMine() {
        isMine = false;
    }

    public void changeMarkedAsBomb() {
        isMarkedAsBomb = !isMarkedAsBomb;
    }

}
