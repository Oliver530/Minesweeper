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

    private final Cell[][] cells;
    private int mines = 0;

    private final HashMap<Cell,Integer> cacheRow = new HashMap<>();
    private final HashMap<Cell,Integer> cacheColumn = new HashMap<>();


    public Board() {
        this(ROWS_MINIMUM, COLUMNS_MINIMUM, GameDifficulty.EASY);
    }

    public Board(int rows, int columns,
                 final GameDifficulty difficulty) {

        rows = Math.max(rows, ROWS_MINIMUM);
        columns = Math.max(columns, COLUMNS_MINIMUM);
        mines = getMines(rows * columns, difficulty);
        cells = BoardBuilder.buildBoard(rows, columns, mines);
        buildCache(cells);
    }

    public Board(final Cell[][] cells, final int mines) {
        this.mines = mines;
        this.cells = cells;
        buildCache(cells);
    }


    public Cell getCell(final int row, final int col) {
        if (!inRange(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        return cells[row][col];
    }

    public int getRows() {
        return cells.length;
    }

    public int getColumns() {
        return cells[0].length;
    }

    public List<Cell> getNeighbourCells(final Cell cell) {
        int row = cacheRow.get(cell);
        int col = cacheColumn.get(cell);
        return getNeighbourCells(row, col);
    }

    public int getNeighbourMineCount(final int row, final int col) {
        return getNeighbourMineCount(getCell(row, col));
    }

    public int getNeighbourMineCount(Cell cell) {
        int amountOfNeighbourMines = 0;
        List<Cell> neighbours = getNeighbourCells(cell);
        for (Cell neighbour : neighbours) {
            if (neighbour.isMineInside()) {
                amountOfNeighbourMines++;
            }
        }
        return amountOfNeighbourMines;
    }

    public void moveMineToRandomCell(final int row, final int col) {
        // ToDo performance optimizations? Could be inefficient if there are many mines
        Cell cell = getCell(row, col);
        if (!cell.isMineInside()) {
            return;
        }
        int rowNew = row;
        int colNew = col;
        Random ran = new Random();
        while (rowNew == row || colNew == col || getCell(rowNew, colNew).isMineInside()) {

            rowNew = ran.nextInt(getRows());
            colNew = ran.nextInt(getColumns());
        }
        cell.removeMine();
        getCell(rowNew, colNew).insertMine();
    }

    public int getMines() {
        return mines;
    }

    private void buildCache(final Cell[][] field) {
        cacheRow.clear();
        cacheColumn.clear();

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

    private boolean inRange(final int row, final int col) {
        if (row < 0 || row >= getRows() || col < 0 || col >= getColumns()) {
            return false;
        } else {
            return cells[row][col] != null;
        }
    }

    private List<Cell> getNeighbourCells(final int row, final int col) {
        List<Cell> neighbours = new ArrayList<>();
        if (inRange(row - 1, col - 1)) {
            neighbours.add(cells[row - 1][col - 1]);
        }
        if (inRange(row - 1, col)) {
            neighbours.add(cells[row - 1][col]);
        }
        if (inRange(row - 1, col + 1)) {
            neighbours.add(cells[row - 1][col + 1]);
        }
        if (inRange(row, col - 1)) {
            neighbours.add(cells[row][col - 1]);
        }
        if (inRange(row, col + 1)) {
            neighbours.add(cells[row][col + 1]);
        }
        if (inRange(row + 1, col - 1)) {
            neighbours.add(cells[row + 1][col - 1]);
        }
        if (inRange(row + 1, col)) {
            neighbours.add(cells[row + 1][col]);
        }
        if (inRange(row + 1, col + 1)) {
            neighbours.add(cells[row + 1][col + 1]);
        }
        return neighbours;
    }

}