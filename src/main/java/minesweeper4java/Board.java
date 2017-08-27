package minesweeper4java;

/**
 * Created by olivergerhardt on 27.08.17.
 */
public class Board {

    public static final int rowCountMinimum = 3;
    public static final int colCountMinimum = 3;

    private final Cell[][] field;
    private final int rowCount;
    private final int colCount;


    public Board(Cell[][] field) {
        if (field.length < rowCountMinimum) {
            throw new IllegalArgumentException("There are not enough rows in the passed field!");
        }
        if (field[0].length < colCountMinimum) {
            throw new IllegalArgumentException("There are not enough columns in the passed field!");
        }
        this.field = field;
        rowCount = field.length;
        colCount = field[0].length;
    }

    public Cell getField(int row, int col) {
        if (!isValidPosition(row, col)) {
            return NullCell.getInstance();
        }
        return field[row][col];
    }

    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < this.rowCount && col >= 0 && col < colCount;
    }

    public int getRowCount() {
        return this.rowCount;
    }

    public int getColCount() {
        return this.colCount;
    }
}
