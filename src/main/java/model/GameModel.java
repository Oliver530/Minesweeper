package model;

import minesweeper4java.MinesweeperGameModel;
import model.cell.Cell;
import model.state.*;

/**
 * Created by olivergerhardt on 28.08.17.
 */
public class GameModel implements MinesweeperGameModel {

    private Board board;
    private GameModelState state;

    public GameModel() {
        state = new GameStateMissingBoard();
    }

    public Board getBoard() {
        return board;
    }

    public GameModelState getState() {
        return state;
    }

    public void setState(GameModelState state) {
        this.state = state;
    }

    public void startGame(Board board) {
        this.board = board;
        state = new GameStateReady();
    }

    public void openCell(int row, int col) {
        state.openCell(this, row, col);
    }

    public void changeMarkedAsBomb(int row, int col) {
        state.changeMarkedAsBomb(this, row, col);
    }

    public void visitAllAndRemoveMarks() {
        state.visitAllAndRemoveMarks(this);
    }

    public Cell getCell(int row, int col) {
        return board.getCell(row, col);
    }

    public int getRowCount() {
        return this.board.getRowCount();
    }

    public int getColCount() {
        return this.board.getColCount();
    }

    public int getCountOfMines() {
        return board.getCountOfMines();
    }

    public void debug(Cell[][] field, int countOfMines) {
        this.board = new Board(field, countOfMines);
        this.state = new GameStateReady();
    }

    @Override
    public boolean gameWon() {
        return state instanceof GameStateWon;
    }

    @Override
    public boolean gameLost() {
        return state instanceof GameStateLost;
    }
}
