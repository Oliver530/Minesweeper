package model;

import model.cell.Cell;
import model.cell.NullCell;
import org.junit.Assert;
import org.junit.Test;
import util.GameDifficulty;

import static org.junit.Assert.assertTrue;

/**
 * Created by olivergerhardt on 27.08.17.
 */
public class BoardTest {

    @Test
    public void createBoardWithTooSmallFieldFails() {
        Board board = new Board(1, GameDifficulty.EASY);
        Assert.assertEquals(Board.MINIMUM_ROWS, board.getRowCount());
        Assert.assertEquals(Board.MINIMUM_COLUMNS, board.getColCount());
    }

    @Test
    public void getFieldInBounds() {
        Board board = new Board(5, GameDifficulty.EASY);
        Cell cell = board.getCell(3, 4);
        Assert.assertNotNull(cell);
    }

    @Test
    public void getFieldNegativeRowOutOfBound() {
        Board board = new Board(4, GameDifficulty.EASY);
        Cell cell = board.getCell(-1, 2);
        assertTrue(cell instanceof NullCell);
    }

    @Test
    public void getFieldPositiveRowOutOfBound() {
        Board board = new Board(4, GameDifficulty.EASY);
        Cell cell = board.getCell(4, 2);
        assertTrue(cell instanceof NullCell);
    }

    @Test
    public void getFieldNegativeColOutOfBound() {
        Board board = new Board(4, GameDifficulty.EASY);
        Cell cell = board.getCell(2, -1);
        assertTrue(cell instanceof NullCell);
    }

    @Test
    public void getFieldPositiveColOutOfBound() {
        Board board = new Board(4, GameDifficulty.EASY);
        Cell cell = board.getCell(4, -1);
        assertTrue(cell instanceof NullCell);
    }

    @Test
    public void cellWithNoNeighbours() {
        Cell[][] field = new Cell[4][4];
        Cell cell = field[0][0] = new Cell();

        Board board = new Board(field, 0);
        Assert.assertTrue(board.getNeighbourCells(cell).isEmpty());
    }

    @Test
    public void cellWithOneNeighbours() {
        Cell[][] field = new Cell[4][4];
        Cell cell = field[0][0] = new Cell();
        field[0][1] = new Cell();

        Board board = new Board(field, 0);
        Assert.assertEquals(1, board.getNeighbourCells(cell).size());
    }

    @Test
    public void cellWithTwoNeighbours() {
        Cell[][] field = new Cell[4][4];
        Cell cell = field[0][0] = new Cell();
        field[0][1] = new Cell();
        field[1][0] = new Cell();

        Board board = new Board(field, 0);
        Assert.assertEquals(2, board.getNeighbourCells(cell).size());
    }

    @Test
    public void cellWithEightNeighbours() {
        Cell[][] field = new Cell[4][4];
        Cell cell = field[1][1] = new Cell();
        field[0][0] = new Cell();
        field[0][1] = new Cell();
        field[0][2] = new Cell();
        field[1][0] = new Cell();
        field[1][2] = new Cell();
        field[2][0] = new Cell();
        field[2][1] = new Cell();
        field[2][2] = new Cell();

        Board board = new Board(field, 0);
        Assert.assertEquals(8, board.getNeighbourCells(cell).size());
    }

    @Test
    public void cellWithNoNeighbourMines() {
        Cell[][] field = new Cell[4][4];
        Cell cell = field[1][1] = new Cell();
        field[0][0] = new Cell();
        field[0][1] = new Cell();
        field[0][2] = new Cell();
        field[1][0] = new Cell();
        field[1][2] = new Cell();
        field[2][0] = new Cell();
        field[2][1] = new Cell();
        field[2][2] = new Cell();

        Board board = new Board(field, 0);
        Assert.assertEquals(0, board.getCountOfNeighbourMines(cell));
    }

    @Test
    public void cellWithOneNeighbourMines() {
        Cell[][] field = new Cell[4][4];
        Cell cell = field[1][1] = new Cell();
        field[0][0] = new Cell();
        field[0][0].setMine();
        field[0][1] = new Cell();
        field[0][2] = new Cell();
        field[1][0] = new Cell();
        field[1][2] = new Cell();
        field[2][0] = new Cell();
        field[2][1] = new Cell();
        field[2][2] = new Cell();

        Board board = new Board(field, 0);
        Assert.assertEquals(1, board.getCountOfNeighbourMines(cell));
    }

    @Test
    public void cellWithThreeNeighbourMines() {
        Cell[][] field = new Cell[4][4];
        Cell cell = field[1][1] = new Cell();
        field[0][0] = new Cell();
        field[0][0].setMine();
        field[0][1] = new Cell();
        field[0][1].setMine();
        field[0][2] = new Cell();
        field[1][0] = new Cell();
        field[1][2] = new Cell();
        field[2][0] = new Cell();
        field[2][1] = new Cell();
        field[2][2] = new Cell();
        field[2][2].setMine();

        Board board = new Board(field, 0);
        Assert.assertEquals(3, board.getCountOfNeighbourMines(cell));
    }

}
