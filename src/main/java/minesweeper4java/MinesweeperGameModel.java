package minesweeper4java;

import model.GameDifficulty;
import model.GameState;
import model.cell.CellInfo;

/**
 * Created by olivergerhardt on 01.09.17.
 */
public interface MinesweeperGameModel {

    // User actions
    void startGame(int dimension, GameDifficulty difficulty);
    void openCell(int row, int col);
    void changeMarkedAsBomb(int row, int col);
    void visitAllAndRemoveMarks();

    // State information
    GameState getState();
    CellInfo getCell(int row, int col);
    int getRowCount();
    int getColCount();
    int getCountOfMines();

}
