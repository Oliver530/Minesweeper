package model;

import model.cell.Cell;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by olivergerhardt on 27.08.17.
 */
public final class CellBuilder {

    private final int countOfRows;
    private final int countOfColumns;
    private final int countOfMines;

    public CellBuilder(final int rowCount, final int colCount, final int minesCount) {
        countOfRows = rowCount;
        countOfColumns = colCount;
        countOfMines = Math.max(0, Math.min(minesCount, countOfRows * countOfColumns));
    }

    public Cell[][] buildBoard() {
        Cell[][] board = new Cell[this.countOfRows][this.countOfColumns];
        populateBoardWithCells(board);
        populateBoardWithMines(board);
        return board;
    }

    private void populateBoardWithCells(final Cell[][] board) {
        for (int row = 0; row < this.countOfRows; row++) {
            for (int col = 0; col < this.countOfColumns; col++) {
                board[row][col] = new Cell();
            }
        }
    }

    private void populateBoardWithMines(final Cell[][] board) {
        int countOfMinesAdded = 0;
        while (countOfMinesAdded < this.countOfMines) {
            int rowRandom = ThreadLocalRandom.current().nextInt(0, this.countOfRows);
            int colRandom = ThreadLocalRandom.current().nextInt(0, this.countOfColumns);
            if (board[rowRandom][colRandom].setMine()) {
                countOfMinesAdded++;
            }
        }
    }

    public int getCountOfMines() {
        return this.countOfMines;
    }
}
