package model.cell;

/**
 * Created by olivergerhardt on 27.08.17.
 */
public class Cell implements CellReadOnly {

    private boolean isOpened;
    private boolean isMineInside;
    private boolean isMarkedAsMineByUser;


    public Cell() {
        isOpened = false;
        isMineInside = false;
        isMarkedAsMineByUser = false;
    }


    @Override
    public boolean isOpened() {
        return isOpened;
    }

    @Override
    public boolean isMineInside() {
        return isMineInside;
    }

    @Override
    public boolean isMarkedAsMineByUser() {
        return isMarkedAsMineByUser;
    }

    public void open() {
        if (isOpened) {
            throw new IllegalStateException("A cell can only be visited once!");
        }
        isOpened = true;
    }
    
    public void insertMine() {
        isMineInside = true;
    }

    public void removeMine() {
        isMineInside = false;
    }

    public void changeMarkedAsBomb() {
        isMarkedAsMineByUser = !isMarkedAsMineByUser;
    }

}
