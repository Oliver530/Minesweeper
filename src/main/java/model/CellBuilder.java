package model;

import model.cell.Cell;

import java.util.Random;
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
        int mines = 0;
        for (int row = 0; row < this.countOfRows; row++) {
            for (int col = 0; col < this.countOfColumns; col++) {
                if (mines < countOfMines) {
                    board[row][col].setMine();
                    mines++;
                }
            }
        }
        shuffleBoard(board);
    }

    private void shuffleBoard(final Cell[][] board) {
        // Fisher–Yates algorithm
        Random ran = new Random();

        for (int row = board.length - 1; row > 0; row--) {
            for (int col = board[row].length - 1; col > 0; col--) {
                int rowRandom = ran.nextInt(row + 1);
                int colRandom = ran.nextInt(col + 1);

                Cell temp = board[row][col];
                board[row][col] = board[rowRandom][colRandom];
                board[rowRandom][colRandom] = temp;

            }
        }
    }

    public int getCountOfMines() {
        return this.countOfMines;
    }
}
