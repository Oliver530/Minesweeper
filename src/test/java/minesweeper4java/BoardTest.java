package minesweeper4java;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by olivergerhardt on 27.08.17.
 */
public class BoardTest {

    @Test(expected = IllegalArgumentException.class)
    public void createBoardWithTooSmallFieldFails() {
        Cell[][] field = new Cell[1][1];
        Board board = new Board(field);
    }

    @Test
    public void getFieldInBounds() {
        Cell[][] field = new Cell[4][5];
        field[3][4] = new Cell();

        Board board = new Board(field);
        Cell cell = board.getCell(3, 4);
        Assert.assertNotNull(cell);
    }

    @Test
    public void getFieldNegativeRowOutOfBound() {
        Cell[][] field = new Cell[4][4];

        Board board = new Board(field);
        Cell cell = board.getCell(-1, 2);
        assertTrue(cell instanceof NullCell);
    }

    @Test
    public void getFieldPositiveRowOutOfBound() {
        Cell[][] field = new Cell[4][4];

        Board board = new Board(field);
        Cell cell = board.getCell(4, 2);
        assertTrue(cell instanceof NullCell);
    }

    @Test
    public void getFieldNegativeColOutOfBound() {
        Cell[][] field = new Cell[4][4];

        Board board = new Board(field);
        Cell cell = board.getCell(2, -1);
        assertTrue(cell instanceof NullCell);
    }

    @Test
    public void getFieldPositiveColOutOfBound() {
        Cell[][] field = new Cell[4][4];

        Board board = new Board(field);
        Cell cell = board.getCell(4, -1);
        assertTrue(cell instanceof NullCell);
    }

    @Test
    public void cellWithNoNeighbours() {
        Cell[][] field = new Cell[4][4];
        Cell cell = field[0][0] = new Cell();

        Board board = new Board(field);
        Assert.assertTrue(board.getNeighbourCells(cell).isEmpty());
    }

    @Test
    public void cellWithOneNeighbours() {
        Cell[][] field = new Cell[4][4];
        Cell cell = field[0][0] = new Cell();
        field[0][1] = new Cell();

        Board board = new Board(field);
        Assert.assertEquals(1, board.getNeighbourCells(cell).size());
    }

    @Test
    public void cellWithTwoNeighbours() {
        Cell[][] field = new Cell[4][4];
        Cell cell = field[0][0] = new Cell();
        field[0][1] = new Cell();
        field[1][0] = new Cell();

        Board board = new Board(field);
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

        Board board = new Board(field);
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

        Board board = new Board(field);
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

        Board board = new Board(field);
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

        Board board = new Board(field);
        Assert.assertEquals(3, board.getCountOfNeighbourMines(cell));
    }

}
