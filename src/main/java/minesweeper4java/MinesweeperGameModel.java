package minesweeper4java;

import model.Board;
import model.cell.CellRO;
import model.state.GameModelState;

/**
 * Created by olivergerhardt on 01.09.17.
 */
public interface MinesweeperGameModel {

    // User actions
    void startGame(Board board);
    void visitAllAndRemoveMarks();

    void openCell(int row, int col);
    void changeMarkedAsBomb(int row, int col);


    // State information
    boolean gameWon();
    boolean gameLost();
    int getRowCount();
    int getColCount();
    int getCountOfMines();

    CellRO getCell(int row, int col);

}
