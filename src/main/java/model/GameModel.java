package model;

import minesweeper4java.MinesweeperGameModel;
import model.cell.Cell;
import model.cell.CellBuilder;
import model.cell.CellInfo;

import java.util.List;

/**
 * Created by olivergerhardt on 28.08.17.
 */
public class GameModel implements MinesweeperGameModel {

    private Board board;
    private GameState state;
    private int countOfMines;

    public GameModel() {
        state = GameState.READY;
    }

    public void startGame(int dimension, GameDifficulty difficulty) {
        board = createBoard(dimension, difficulty);
        state = GameState.RUNNING;
    }

    public GameState getState() {
        return state;
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

        if (!cell.isVisited()) {
            cell.visit();

            if (board.getCountOfNeighbourMines(cell) == 0) {
                openCellR(cell);
            }
        } else {
            openCellR(cell);
        }
        checkWinCondition();

    }

    public CellInfo getCell(int row, int col) {
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

    public void visitAllAndRemoveMarks() {
        for (int row = 0; row < board.getRowCount(); row++) {
            for (int col = 0; col < board.getColCount(); col++) {
                Cell cell = board.getCell(row, col);
                if (!cell.isVisited()) {
                    cell.visit();
                }
                if (cell.isMarkedAsBomb()) {
                    cell.changeMarkedAsBomb();
                }
            }
        }
    }

    private Board createBoard(int dimension, GameDifficulty difficulty) {
        this.countOfMines = getCountOfMinesByDifficulty(dimension, difficulty);

        CellBuilder cellBuilder = new CellBuilder(dimension, this.countOfMines);
        return new Board(cellBuilder.buildBoard());
    }

    private int getCountOfMinesByDifficulty(int dimension, GameDifficulty difficulty) {
        return (int) (difficulty.getPercentage() * (dimension * dimension));
    }

    private void openCellR(Cell cell) {
        List<Cell> neighbours = board.getNeighbourCells(cell);
        for (Cell neighbour : neighbours) {
            if (neighbour.isVisited()) {
                continue;
            } else if (cell.isMine() && !cell.isMarkedAsBomb()) {
                state = GameState.LOST;
                return;
            } else {
                neighbour.visit();

                if (board.getCountOfNeighbourMines(neighbour) == 0) {
                    openCellR(neighbour);
                }
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

    public void debug(Board board, int countOfMines) {
        this.board = board;
        this.countOfMines = countOfMines;
        this.state = GameState.RUNNING;
    }
}
