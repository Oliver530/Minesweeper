package model;

import model.cell.Cell;
import util.GameDifficulty;

import java.util.*;

/**
 * Created by olivergerhardt on 27.08.17.
 */
public final class Board {

    public static final int ROWS_MINIMUM = 4;
    public static final int COLUMNS_MINIMUM = 4;

    private Cell[][] field;
    private int mines = 0;

    HashMap<Cell,Integer> cacheRow = new HashMap<Cell,Integer>();
    HashMap<Cell,Integer> cacheColumn = new HashMap<Cell,Integer>();

    /*
    Default constructor will use default values
     */
    public Board() {
        this(ROWS_MINIMUM, COLUMNS_MINIMUM, GameDifficulty.EASY);
    }


    public Board(int rows, int columns,
                 final GameDifficulty difficulty) {

        rows = Math.max(rows, ROWS_MINIMUM);
        columns = Math.max(columns, COLUMNS_MINIMUM);
        mines = getMines(rows * columns, difficulty);

        setField(BoardBuilder.buildBoard(rows, columns, mines));
    }

    public Board(final Cell[][] field, final int mineCount) {
        this.mines = mineCount;
        setField(field);
    }

    private void setField(final Cell[][] field) {
        this.field = field;
        for (int row = 0; row < getRows(); row++) {
            for (int col = 0; col < getColumns(); col++) {
                cacheRow.put(field[row][col], row);
                cacheColumn.put(field[row][col], col);
            }
        }

    }

    private int getMines(final int cells, final GameDifficulty difficulty) {
        return (int) (difficulty.getPercentage() * cells);
    }

    public Cell getCell(final int row, final int col) {
        if (!inRange(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        return field[row][col];
    }

    private boolean inRange(final int row, final int col) {
        if (row < 0 || row >= getRows() || col < 0 || col >= getColumns()) {
            return false;
        } else {
            return field[row][col] != null;
        }
    }

    public int getRows() {
        return field.length;
    }

    public int getColumns() {
        return field[0].length;
    }

    public List<Cell> getNeighbourCells(final Cell cell) {
        int row = cacheRow.get(cell);
        int col = cacheColumn.get(cell);
        return getNeighbourCells(row, col);
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

    public int getNeighbourMineCount(final int row, final int col) {
        return getNeighbourMineCount(getCell(row, col));
    }

    public int getNeighbourMineCount(Cell cell) {
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
        // ToDo performance optimizations? Could be inefficient if there are many mines
        Cell cell = getCell(row, col);
        if (!cell.isMine()) {
            return;
        }
        int rowNew = row;
        int colNew = col;
        Random ran = new Random();
        while (rowNew == row || colNew == col || getCell(rowNew, colNew).isMine()) {

            rowNew = ran.nextInt(getRows());
            colNew = ran.nextInt(getColumns());
        }
        cell.removeMine();
        getCell(rowNew, colNew).setMine();
    }

    public int getMines() {
        return mines;
    }

}