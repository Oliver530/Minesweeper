package minesweeper4java;

/**
 * Created by olivergerhardt on 27.08.17.
 */
public class NullCell extends Cell {

    // Singleton
    private static NullCell instance;

    private NullCell() {};

    public static Cell getInstance() {
        if (instance == null) {
            instance = new NullCell();
        }
        return instance;
    }
}