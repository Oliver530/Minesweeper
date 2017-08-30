package minesweeper4java;

import java.util.List;

/**
 * Created by olivergerhardt on 28.08.17.
 */
public class GameModel {

    private Board board;
    private GameState state = GameState.READY;
    private int countOfMines;


    public GameState getState() {
        return state;
    }

    public void startGame(int dimension, GameDifficulty difficulty) {
        this.countOfMines = getCountOfMinesByDifficulty(dimension, difficulty);
        CellBuilder cellBuilder = new CellBuilder(dimension, this.countOfMines);
        this.board = new Board(cellBuilder.buildBoard());
        this.state = GameState.RUNNING;
    }

    private int getCountOfMinesByDifficulty(int dimension, GameDifficulty difficulty) {
        return (int) (difficulty.getPercentage() * (dimension * dimension));
    }

    public void openCell(int row, int col) {
        if (state != GameState.RUNNING) {
            throw new IllegalStateException("GameState is + " + state);
        }

        Cell cell = board.getCell(row, col);
        if (cell.isMine()) {
            state = GameState.LOST;
            return;
        }
        cell.visit();

        if (board.getCountOfNeighbourMines(cell) == 0) {
            openCellR(cell);
        }

        checkWinCondition();
    }

    public void openCellR(Cell cell) {
        List<Cell> neighbours = board.getNeighbourCells(cell);
        for (Cell neighbour : neighbours) {
            if (neighbour.isVisited()) {
                continue;
            }
            neighbour.visit();

            if (board.getCountOfNeighbourMines(neighbour) == 0) {
                openCellR(neighbour);
            }
        }
    }

    private void checkWinCondition() {
        for (int row = 0; row < board.getRowCount(); row++) {
            for (int col = 0; col < board.getColCount(); col++) {
                if (!board.getCell(row, col).isMine() && !board.getCell(row, col).isVisited()) {
                    return;
                }
            }
        }
        state = GameState.WON;
    }

    public CellInfo getCellInfo(int row, int col) {
        Cell cell = board.getCell(row, col);
        return new CellInfo(cell, board.getCountOfNeighbourMines(cell));
    }

    public int getRowCount() {
        return this.board.getRowCount();
    }

    public int getColCount() {
        return this.board.getColCount();
    }

    public int getCountOfMines() {
        return this.countOfMines;
    }

    public void changeMarkedAsBomb(int row, int col) {
        Cell cell = board.getCell(row, col);
        cell.changeMarkedAsBomb();
    }
}
