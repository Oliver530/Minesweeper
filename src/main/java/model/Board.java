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

    public static final int ROWS_MINIMUM = 4;
    public static final int COLUMNS_MINIMUM = 4;

    private final Cell[][] field;
    private final int rows;
    private final int columns;
    private final int mines;

    /*
    Default constructor will use default values
     */
    public Board() {
        this(ROWS_MINIMUM, COLUMNS_MINIMUM, GameDifficulty.EASY);
    }


    public Board(final int rows, final int columns,
                 final GameDifficulty difficulty) {
        this.rows = Math.max(rows, ROWS_MINIMUM);
        this.columns = Math.max(columns, COLUMNS_MINIMUM);
        this.mines = getMines(difficulty);

        CellBuilder builder = new CellBuilder(this.rows, this.columns, mines);
        field = builder.buildBoard();
        setNeighbourCount();
    }

    public Board(final Cell[][] fieldOfCells, final int mineCount) {
        this.rows = fieldOfCells.length;
        this.columns = fieldOfCells[0].length;
        this.mines = mineCount;
        this.field = fieldOfCells;
    }

    private int getMines(final GameDifficulty difficulty) {
        return (int) (difficulty.getPercentage() * getCellCount());
    }

    public Cell getCell(final int row, final int col) {
        if (!inRange(row, col)) {
            return NullCell.getInstance();
        }
        return field[row][col];
    }

    private void setNeighbourCount() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                Cell cell = getCell(row, col);
                cell.setNeighbourMines(getNeighbourMineCount(cell));
            }
        }
    }

    private boolean inRange(final int row, final int col) {
        if (row < 0 || row >= rows) {
            return false;
        }

        if (col < 0 || col >= columns) {
            return false;
        }

        return field[row][col] != null;
    }

    public int getRows() {
        return this.rows;
    }

    public int getColumns() {
        return this.columns;
    }

    public List<Cell> getNeighbourCells(final Cell cell) {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                if (getCell(row, col) == cell) {
                    return getNeighbourCells(row, col);
                }
            }

        }
        return Collections.emptyList();
    }

    private List<Cell> getNeighbourCells(final int row, final int col) {
        List<Cell> neighbours = new ArrayList<>();
        if (inRange(row - 1, col - 1)) {
            neighbours.add(field[row - 1][col - 1]);
        }
        if (inRange(row - 1, col)) {
            neighbours.add(field[row - 1][col]);
        }
        if (inRange(row - 1, col + 1)) {
            neighbours.add(field[row - 1][col + 1]);
        }
        if (inRange(row, col - 1)) {
            neighbours.add(field[row][col - 1]);
        }
        if (inRange(row, col + 1)) {
            neighbours.add(field[row][col + 1]);
        }
        if (inRange(row + 1, col - 1)) {
            neighbours.add(field[row + 1][col - 1]);
        }
        if (inRange(row + 1, col)) {
            neighbours.add(field[row + 1][col]);
        }
        if (inRange(row + 1, col + 1)) {
            neighbours.add(field[row + 1][col + 1]);
        }
        return neighbours;
    }

    public int getNeighbourMineCount(final Cell cell) {
        int count = 0;
        List<Cell> neighbours = getNeighbourCells(cell);
        for (Cell neighbour : neighbours) {
            if (neighbour.isMine()) {
                count++;
            }
        }
        return count;
    }

    public void moveMineToRandomCell(final int row, final int col) {
        // ToDo performance optimizations? Could be inefficent if there are many mines
        Cell cell = getCell(row, col);
        if (!cell.isMine()) {
            return;
        }
        int rowNew = row;
        int colNew = col;
        Random ran = new Random();
        while (rowNew == row || colNew == col || getCell(rowNew, colNew).isMine()) {

            rowNew = ran.nextInt(rows);
            colNew = ran.nextInt(columns);
        }
        removeMine(cell);
        addMine(getCell(rowNew, colNew));
    }

    private void removeMine(Cell cell) {
        cell.removeMine();
        List<Cell> neighbours = getNeighbourCells(cell);
        for (Cell neighbour : neighbours) {
            neighbour.setNeighbourMines(neighbour.getNeighbourMines() - 1);
        }
    }

    private void addMine(Cell cell) {
        cell.setMine();
        List<Cell> neighbours = getNeighbourCells(cell);
        for (Cell neighbour : neighbours) {
            neighbour.setNeighbourMines(neighbour.getNeighbourMines() + 1);
        }
    }

    public int getMines() {
        return mines;
    }

    private int getCellCount() {
        return rows * columns;
    }
}