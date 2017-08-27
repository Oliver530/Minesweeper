package minesweeper4java;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
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
        Cell cell = board.getField(3, 4);
        Assert.assertNotNull(cell);
    }

    @Test
    public void getFieldNegativeRowOutOfBound() {
        Cell[][] field = new Cell[4][4];

        Board board = new Board(field);
        Cell cell = board.getField(-1, 2);
        assertTrue(cell instanceof NullCell);
    }

    @Test
    public void getFieldPositiveRowOutOfBound() {
        Cell[][] field = new Cell[4][4];

        Board board = new Board(field);
        Cell cell = board.getField(4, 2);
        assertTrue(cell instanceof NullCell);
    }

    @Test
    public void getFieldNegativeColOutOfBound() {
        Cell[][] field = new Cell[4][4];

        Board board = new Board(field);
        Cell cell = board.getField(2, -1);
        assertTrue(cell instanceof NullCell);
    }

    @Test
    public void getFieldPositiveColOutOfBound() {
        Cell[][] field = new Cell[4][4];

        Board board = new Board(field);
        Cell cell = board.getField(4, -1);
        assertTrue(cell instanceof NullCell);
    }

}
