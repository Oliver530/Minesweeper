package model;

import model.cell.Cell;
import model.cell.NullCell;
import util.GameDifficulty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by olivergerhardt on 27.08.17.
 */
public final class Board {

    public static final int MINIMUM_ROWS = 4;
    public static final int MINIMUM_COLUMNS = 4;

    private final Cell[][] field;
    private final int rowCount;
    private final int colCount;
    private final int countOfMines;

    /*
    Default constructor will use default values
     */
    public Board() {
        this(MINIMUM_ROWS, MINIMUM_COLUMNS, GameDifficulty.EASY);
    }


    public Board(final int countOfRows, final int countOfColumns,
                 final GameDifficulty difficulty) {
        rowCount = Math.max(countOfRows, MINIMUM_ROWS);
        colCount = Math.max(countOfColumns, MINIMUM_COLUMNS);
        countOfMines = getCountOfMinesByDifficulty(difficulty);

        CellBuilder builder = new CellBuilder(rowCount, colCount, countOfMines);
        field = builder.buildBoard();
        setNeighbourCount();
    }

    public Board(final Cell[][] fieldOfCells, final int mineCount) {
        this.rowCount = fieldOfCells.length;
        this.colCount = fieldOfCells[0].length;
        this.countOfMines = mineCount;
        this.field = fieldOfCells;
    }

    private int getCountOfMinesByDifficulty(final GameDifficulty difficulty) {
        return (int) (difficulty.getPercentage() * getCountOfCells());
    }

    public Cell getCell(final int row, final int col) {
        if (!isValidPosition(row, col)) {
            return NullCell.getInstance();
        }
        return field[row][col];
    }

    private void setNeighbourCount() {
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                Cell cell = getCell(row, col);
                cell.setCountOfNeighbourMines(getCountOfNeighbourMines(cell));
            }
        }
    }

    private boolean isValidPosition(final int row, final int col) {
        if (row < 0 || row >= rowCount) {
            return false;
        }

        if (col < 0 || col >= colCount) {
            return false;
        }

        return field[row][col] != null;
    }

    public int getRowCount() {
        return this.rowCount;
    }

    public int getColCount() {
        return this.colCount;
    }

    public List<Cell> getNeighbourCells(final Cell cell) {
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                if (getCell(row, col) == cell) {
                    return getNeighbourCells(row, col);
                }
            }

        }
        return Collections.emptyList();
    }

    private List<Cell> getNeighbourCells(final int row, final int col) {
        List<Cell> neighbours = new ArrayList<>();
        if (isValidPosition(row - 1, col - 1)) {
            neighbours.add(field[row - 1][col - 1]);
        }
        if (isValidPosition(row - 1, col)) {
            neighbours.add(field[row - 1][col]);
        }
        if (isValidPosition(row - 1, col + 1)) {
            neighbours.add(field[row - 1][col + 1]);
        }
        if (isValidPosition(row, col - 1)) {
            neighbours.add(field[row][col - 1]);
        }
        if (isValidPosition(row, col + 1)) {
            neighbours.add(field[row][col + 1]);
        }
        if (isValidPosition(row + 1, col - 1)) {
            neighbours.add(field[row + 1][col - 1]);
        }
        if (isValidPosition(row + 1, col)) {
            neighbours.add(field[row + 1][col]);
        }
        if (isValidPosition(row + 1, col + 1)) {
            neighbours.add(field[row + 1][col + 1]);
        }
        return neighbours;
    }

    public int getCountOfNeighbourMines(final Cell cell) {
        int countOfNeighbourMines = 0;
        List<Cell> neighbours = getNeighbourCells(cell);
        for (Cell neighbour : neighbours) {
            if (neighbour.isMine()) {
                countOfNeighbourMines++;
            }
        }
        return countOfNeighbourMines;
    }

    public void moveMineToRandomCell(final int row, final int col) {
        Cell cell = getCell(row, col);
        if (!cell.isMine()) {
            return;
        }
        int rowNew = row;
        int colNew = col;
        Random ran = new Random();
        while (rowNew == row || colNew == col || getCell(rowNew, colNew).isMine()) {

            rowNew = ran.nextInt(rowCount);
            colNew = ran.nextInt(colCount);
        }
        cell.removeMine();
        getCell(rowNew, colNew).setMine();
    }

    public int getCountOfMines() {
        return countOfMines;
    }

    private int getCountOfCells() {
        return rowCount * colCount;
    }
}
