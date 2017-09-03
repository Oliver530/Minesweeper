package model;

import model.cell.Cell;

import java.util.Random;

/**
 * Created by olivergerhardt on 27.08.17.
 */
public final class CellBuilder {

    private final int rows;
    private final int columns;
    private final int mines;

    public CellBuilder(final int rowCount, final int colCount, final int minesCount) {
        rows = rowCount;
        columns = colCount;
        mines = Math.max(0, Math.min(minesCount, rows * columns));
    }

    public Cell[][] buildBoard() {
        Cell[][] board = new Cell[this.rows][this.columns];
        populateBoardWithCells(board);
        populateBoardWithMines(board);
        return board;
    }

    private void populateBoardWithCells(final Cell[][] board) {
        for (int row = 0; row < this.rows; row++) {
            for (int col = 0; col < this.columns; col++) {
                board[row][col] = new Cell();
            }
        }
    }

    private void populateBoardWithMines(final Cell[][] board) {
        int mines = 0;
        for (int row = 0; row < this.rows; row++) {
            for (int col = 0; col < this.columns; col++) {
                if (mines < this.mines) {
                    board[row][col].setMine();
                    mines++;
                }
            }
        }
        shuffleBoard(board);
    }

    private void shuffleBoard(final Cell[][] board) {
        // Fisher–Yates algorithm
        Random random = new Random();

        for (int row = board.length - 1; row > 0; row--) {
            for (int col = board[row].length - 1; col > 0; col--) {
                int rowRandom = random.nextInt(row + 1);
                int colRandom = random.nextInt(col + 1);

                Cell temp = board[row][col];
                board[row][col] = board[rowRandom][colRandom];
                board[rowRandom][colRandom] = temp;

            }
        }
    }

    public int getMines() {
        return this.mines;
    }
}
