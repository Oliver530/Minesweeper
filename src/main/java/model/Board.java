package minesweeper4java;

import java.util.ArrayList;
import java.util.List;

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

    public Cell getCell(int row, int col) {
        if (!isValidPosition(row, col)) {
            return NullCell.getInstance();
        }
        return field[row][col];
    }

    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < this.rowCount && col >= 0 && col < colCount && field[row][col] instanceof Cell;
    }

    public int getRowCount() {
        return this.rowCount;
    }

    public int getColCount() {
        return this.colCount;
    }

    public List<Cell> getNeighbourCells(Cell cell) {
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                if (getCell(row, col) == cell) {
                    return getNeighbourCells(row, col);
                }
            }

        }
        return new ArrayList<Cell>();
    }

    private List<Cell> getNeighbourCells(int row, int col) {
        List<Cell> neighbours = new ArrayList<Cell>();
        if (isValidPosition(row - 1, col - 1)) {
            neighbours.add(field[row - 1][col - 1]);
        }
        if (isValidPosition(row - 1, col)) {
            neighbours.add(field[row - 1][col]);
        }
        if (isValidPosition(row - 1, col + 1)) {
            neighbours.add(field[row - 1][col + 1]);
        }
        if (isValidPosition(row, col - 1)) {
            neighbours.add(field[row][col - 1]);
        }
        if (isValidPosition(row, col + 1)) {
            neighbours.add(field[row][col + 1]);
        }
        if (isValidPosition(row + 1, col - 1)) {
            neighbours.add(field[row + 1][col - 1]);
        }
        if (isValidPosition(row + 1, col)) {
            neighbours.add(field[row + 1][col]);
        }
        if (isValidPosition(row + 1, col + 1)) {
            neighbours.add(field[row + 1][col + 1]);
        }
        return neighbours;
    }

    public int getCountOfNeighbourMines(Cell cell) {
        int countOfNeighbourMines = 0;
        List<Cell> neighbours = getNeighbourCells(cell);
        for (Cell neighbour : neighbours) {
            if (neighbour.isMine()) {
                countOfNeighbourMines++;
            }
        }
        return countOfNeighbourMines;
    }
}
