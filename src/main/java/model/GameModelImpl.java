package model;

import minesweeper.GameInfoProvider;
import minesweeper.GameModel;
import model.cell.Cell;
import model.state.GameModelState;
import model.state.GameStateLost;
import model.state.GameStateReady;
import model.state.GameStateWon;

/**
 * Created by olivergerhardt on 28.08.17.
 */
public final class GameModelImpl implements GameModel, GameInfoProvider {

    private Board board;            // Board contains cell objects (abstraction of two dimensional array)
    private GameModelState state;   // State of game (state pattern)


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
    public int getNeighbourMines(int row, int col) {
        return board.getNeighbourMineCount(row, col);
    }

    @Override
    public Cell getCell(final int row, final int col) {
        return board.getCell(row, col);
    }

    @Override
    public int getRowCount() {
        return this.board.getRows();
    }

    @Override
    public int getColCount() {
        return this.board.getColumns();
    }

    @Override
    public int getCountOfMines() {
        return board.getMines();
    }

    @Override
    public boolean gameWon() {
        return state instanceof GameStateWon;
    }

    @Override
    public boolean gameLost() {
        return state instanceof GameStateLost;
    }

    @Override
    public GameInfoProvider getGameInfoProvider() {
        return this;
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

    public int getNeighbourMines(Cell cell) {
        return board.getNeighbourMineCount(cell);
    }

    public void debug(final Cell[][] field, final int countOfMines) {
        setBoard(new Board(field, countOfMines));
    }

}
