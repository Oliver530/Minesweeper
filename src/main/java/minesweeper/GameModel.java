package minesweeper;

import model.Board;
import model.cell.CellReadOnly;

/**
 * Created by olivergerhardt on 01.09.17.
 */
public interface GameModel {

    void setBoard(Board board);

    void openCell(int row, int col);
    void changeMarkedAsBomb(int row, int col);

    void visitAllAndRemoveMarks();

    int getNeighbourMines(int row, int col);
    CellReadOnly getCell(int row, int col);
    GameInfoProvider getGameInfoProvider();

}
