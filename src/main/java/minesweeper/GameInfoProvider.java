package minesweeper;

// Read Only interface
public interface GameInfoProvider {

    int getRowCount();
    int getColCount();
    int getCountOfMines();

    boolean gameWon();
    boolean gameLost();

}
