package model;

import minesweeper4java.GameModel;
import model.cell.Cell;
import model.state.GameModelState;
import model.state.GameStateLost;
import model.state.GameStateReady;
import model.state.GameStateWon;

/**
 * Created by olivergerhardt on 28.08.17.
 */
public final class GameModelImpl implements GameModel {

    // Board contains cell objects (abstraction of two dimensional array)
    private Board board;

    // State of game (state pattern)
    private GameModelState state;


    // A new game model will use a default board
    public GameModelImpl() {
        setBoard(new Board());
    }


    // Inject board and start game
    @Override
    public void setBoard(final Board board) {
        this.board = board;
        state = new GameStateReady();
    }

    @Override
    public void openCell(final int row, final int col) {
        state.openCell(this, row, col);
    }

    @Override
    public void changeMarkedAsBomb(final int row, final int col) {
        state.changeMarkedAsBomb(this, row, col);
    }

    @Override
    public void visitAllAndRemoveMarks() {
        state.visitAllAndRemoveMarks(this);
    }

    @Override
    public Cell getCell(final int row, final int col) {
        return board.getCell(row, col);
    }

    @Override
    public int getRowCount() {
        return this.board.getRowCount();
    }

    @Override
    public int getColCount() {
        return this.board.getColCount();
    }

    @Override
    public int getCountOfMines() {
        return board.getCountOfMines();
    }

    @Override
    public boolean gameWon() {
        return state instanceof GameStateWon;
    }

    @Override
    public boolean gameLost() {
        return state instanceof GameStateLost;
    }

    public GameModelState getState() {
        return state;
    }

    public void setState(final GameModelState state) {
        this.state = state;
    }

    // Board is needed by state pattern
    public Board getBoard() {
        return board;
    }

    public void debug(final Cell[][] field, final int countOfMines) {
        setBoard(new Board(field, countOfMines));
    }


}
