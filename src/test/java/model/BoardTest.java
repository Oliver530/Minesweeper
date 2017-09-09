package model;

import model.cell.Cell;
import org.junit.Assert;
import org.junit.Test;
import util.GameDifficulty;

/**
 * Created by olivergerhardt on 27.08.17.
 */
public class BoardTest {

    @Test
    public void createBoardWithTooSmallFieldFails() {
        Board board = new Board(1, 1, GameDifficulty.EASY);
        Assert.assertEquals(Board.ROWS_MINIMUM, board.getRows());
        Assert.assertEquals(Board.COLUMNS_MINIMUM, board.getColumns());
    }

    @Test
    public void getFieldInBounds() {
        Board board = new Board(5, 5, GameDifficulty.EASY);
        Cell cell = board.getCell(3, 4);
        Assert.assertNotNull(cell);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getFieldNegativeRowOutOfBound() {
        Board board = new Board(4, 4, GameDifficulty.EASY);
        board.getCell(-1, 2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getFieldPositiveRowOutOfBound() {
        Board board = new Board(4, 4, GameDifficulty.EASY);
        board.getCell(4, 2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getFieldNegativeColOutOfBound() {
        Board board = new Board(4, 4, GameDifficulty.EASY);
        board.getCell(2, -1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getFieldPositiveColOutOfBound() {
        Board board = new Board(4, 4, GameDifficulty.EASY);
        board.getCell(4, -1);
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
        Assert.assertEquals(0, board.getNeighbourMineCount(cell));
    }

    @Test
    public void cellWithOneNeighbourMines() {
        Cell[][] field = new Cell[4][4];
        Cell cell = field[1][1] = new Cell();
        field[0][0] = new Cell();
        field[0][0].insertMine();
        field[0][1] = new Cell();
        field[0][2] = new Cell();
        field[1][0] = new Cell();
        field[1][2] = new Cell();
        field[2][0] = new Cell();
        field[2][1] = new Cell();
        field[2][2] = new Cell();

        Board board = new Board(field, 0);
        Assert.assertEquals(1, board.getNeighbourMineCount(cell));
    }

    @Test
    public void cellWithThreeNeighbourMines() {
        Cell[][] field = new Cell[4][4];
        Cell cell = field[1][1] = new Cell();
        field[0][0] = new Cell();
        field[0][0].insertMine();
        field[0][1] = new Cell();
        field[0][1].insertMine();
        field[0][2] = new Cell();
        field[1][0] = new Cell();
        field[1][2] = new Cell();
        field[2][0] = new Cell();
        field[2][1] = new Cell();
        field[2][2] = new Cell();
        field[2][2].insertMine();

        Board board = new Board(field, 0);
        Assert.assertEquals(3, board.getNeighbourMineCount(cell));
    }

}
