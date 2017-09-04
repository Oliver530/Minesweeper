package model;

import model.cell.Cell;
import java.util.Random;

/**
 * Created by olivergerhardt on 27.08.17.
 */
public final class BoardBuilder {

    private static Random random = new Random();

    public static Cell[][] buildBoard(final int rows, final int columns, int mines) {
        mines = Math.max(0, Math.min(mines, rows * columns));

        Cell[][] board = new Cell[rows][columns];
        populateBoardWithCells(board);
        populateBoardWithMines(board, mines);
        return board;
    }

    private static void populateBoardWithCells(final Cell[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                board[row][col] = new Cell();
            }
        }
    }

    private static void populateBoardWithMines(final Cell[][] board, final int mines) {
        int numberOfMinesSet = 0;
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (numberOfMinesSet < mines) {
                    board[row][col].insertMine();
                    numberOfMinesSet++;
                }
            }
        }
        shuffleBoard(board);
    }

    private static void shuffleBoard(final Cell[][] board) {
        // Fisher–Yates algorithm
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

}
