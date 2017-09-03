package model.cell;

import model.BoardBuilder;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by olivergerhardt on 27.08.17.
 */
public class BoardBuilderTest {

    private int countMines(Cell[][] field) {
        int mines = 0;
        for (int row = 0; row < field.length; row++) {
            for (int col = 0; col < field[0].length; col++) {
                if (field[row][col].isMine()) {
                    mines++;
                }
            }
        }
        return mines;
    }

    @Test
    public void createBoardBuilderWithNegativeMines() {
        Cell[][] field = BoardBuilder.buildBoard(5, 5, -2);
        Assert.assertEquals(0, countMines(field));
    }

    @Test
    public void createBoardBuilderWithTwoMines() {
        Cell[][] field = BoardBuilder.buildBoard(4, 6, 2);
        Assert.assertEquals(2, countMines(field));
        Assert.assertEquals(4, field.length);
        Assert.assertEquals(6, field[0].length);
    }

    @Test
    public void createBoardBuilderWithMoreMinesThanCells() {
        Cell[][] field = BoardBuilder.buildBoard(5, 5, 5*5+2);
        Assert.assertEquals(5*5, countMines(field));
    }

    @Test
    public void buildBoardWithDimensionTwoAndZeroMines() {
        Cell[][] field = BoardBuilder.buildBoard(2, 2, 0);
        Assert.assertEquals(0, countMines(field));
        Assert.assertEquals(2, field.length);
        Assert.assertEquals(2, field[0].length);
        Assert.assertNotNull(field[0][0]);
        Assert.assertNotNull(field[1][0]);
        Assert.assertNotNull(field[0][1]);
        Assert.assertNotNull(field[1][1]);
    }

    @Test
    public void buildBoardWithDimensionFiveAndZeroMines() {
        Cell[][] field = BoardBuilder.buildBoard(5, 5, 0);
        Assert.assertEquals(0, countMines(field));
        Assert.assertEquals(5, field.length);
        Assert.assertEquals(5, field[0].length);
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                Assert.assertNotNull(field[i][j]);
            }
        }
    }

    @Test
    public void buildBoardWithDimensionFiveAnd2Mines() {
        Cell[][] field = BoardBuilder.buildBoard(7, 4, 2);
        Assert.assertEquals(2, countMines(field));
        Assert.assertEquals(7, field.length);
        Assert.assertEquals(4, field[0].length);
    }


}
