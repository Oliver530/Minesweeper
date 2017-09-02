package model.cell;

/**
 * Created by olivergerhardt on 27.08.17.
 */
public final class NullCell extends Cell {

    // Singleton
    private static NullCell instance;

    public static Cell getInstance() {
        if (instance == null) {
            instance = new NullCell();
        }
        return instance;
    }

    @Override
    public boolean isVisited() {
        return false;
    }

    @Override
    public boolean isMine() {
        return false;
    }
}
