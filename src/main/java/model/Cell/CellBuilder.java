package model.Cell;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by olivergerhardt on 27.08.17.
 */
public class CellBuilder {

    public static final int dimensionMinimum = 2;
    private final int dimension;
    private final int countOfMines;

    public CellBuilder(int dimension, int countOfMines) {
        this.dimension = Math.max(dimension, dimensionMinimum);
        this.countOfMines = Math.max(0, Math.min(countOfMines, this.dimension * this.dimension));
    }

    public Cell[][] buildBoard() {
        Cell[][] board = new Cell[dimension][dimension];
        populateBoardWithCells(board);
        populateBoardWithMines(board);
        return board;
    }

    private void populateBoardWithCells(Cell[][] board) {
        for (int row = 0; row < dimension; row++) {
            for (int col = 0; col < dimension; col++) {
                board[row][col] = new Cell();
            }
        }
    }

    private void populateBoardWithMines(Cell[][] board) {
        int countOfMinesAdded = 0;
        while (countOfMinesAdded < this.countOfMines) {
            int rowRandom = ThreadLocalRandom.current().nextInt(0, this.dimension);
            int colRandom = ThreadLocalRandom.current().nextInt(0, this.dimension);
            if (board[rowRandom][colRandom].setMine()) {
                countOfMinesAdded++;
            }
        }
    }

    public int getDimension() {
        return this.dimension;
    }

    public int getCountOfMines() {
        return this.countOfMines;
    }
}
