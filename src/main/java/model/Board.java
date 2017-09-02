package model;

import model.cell.Cell;
import model.cell.CellBuilder;
import model.cell.NullCell;
import util.GameDifficulty;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by olivergerhardt on 27.08.17.
 */
public class Board {

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
        this(MINIMUM_ROWS, GameDifficulty.EASY);
    }


    public Board(int dimension, GameDifficulty difficulty) {
        this.rowCount = Math.max(dimension, MINIMUM_ROWS);
        this.colCount = Math.max(dimension, MINIMUM_COLUMNS);
        this.countOfMines = getCountOfMinesByDifficulty(dimension, difficulty);

        CellBuilder cellBuilder = new CellBuilder(this.rowCount, countOfMines);
        this.field = cellBuilder.buildBoard();
        setNeighbourCount();
    }

    public Board(Cell[][] field, int countOfMines) {
        this.rowCount = field.length;
        this.colCount = field[0].length;
        this.countOfMines = countOfMines;
        this.field = field;
    }

    private int getCountOfMinesByDifficulty(int dimension, GameDifficulty difficulty) {
        return (int) (difficulty.getPercentage() * (dimension * dimension));
    }

    public Cell getCell(int row, int col) {
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

    private boolean isValidPosition(int row, int col) {
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

    public List<Cell> getNeighbourCells(Cell cell) {
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                if (getCell(row, col) == cell) {
                    return getNeighbourCells(row, col);
                }
            }

        }
        return new ArrayList<>();
    }

    private List<Cell> getNeighbourCells(int row, int col) {
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

    public int getCountOfNeighbourMines(Cell cell) {
        int countOfNeighbourMines = 0;
        List<Cell> neighbours = getNeighbourCells(cell);
        for (Cell neighbour : neighbours) {
            if (neighbour.isMine()) {
                countOfNeighbourMines++;
            }
        }
        return countOfNeighbourMines;
    }

    public void moveMineToRandomCell(int row, int col) {
        Cell cell = getCell(row, col);
        if (!cell.isMine()) {
            return;
        }
        int rowNew = row;
        int colNew = col;
        while (rowNew == row || colNew == col || getCell(rowNew, colNew).isMine()) {
            rowNew = ThreadLocalRandom.current().nextInt(0, rowCount);
            colNew = ThreadLocalRandom.current().nextInt(0, colCount);
        }
        cell.removeMine();
        getCell(rowNew, colNew).setMine();
    }

    public int getCountOfMines() {
        return countOfMines;
    }
}
