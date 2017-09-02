package model.cell;

import model.CellBuilder;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by olivergerhardt on 27.08.17.
 */
public class CellBuilderTest {

    @Test
    public void createCellBuilderWithNegativeMines() {
        CellBuilder builder = new CellBuilder(5, 5, -2);
        Assert.assertEquals(0, builder.getCountOfMines());
    }

    @Test
    public void createCellBuilderWithTwoMines() {
        CellBuilder builder = new CellBuilder(5, 5, 2);
        Assert.assertEquals(2, builder.getCountOfMines());
    }

    @Test
    public void createCellBuilderWithMoreMinesThanCells() {
        CellBuilder builder = new CellBuilder(5, 5, 5*5+2);
        Assert.assertEquals(5*5, builder.getCountOfMines());
    }

    @Test
    public void buildBoardWithDimensionTwoAndZeroMines() {
        CellBuilder builder = new CellBuilder(2, 2, 0);
        Cell[][] board = builder.buildBoard();
        Assert.assertEquals(2, board.length);
        Assert.assertEquals(2, board[0].length);
        Assert.assertNotNull(board[0][0]);
    }

    @Test
    public void buildBoardWithDimensionFiveAndZeroMines() {
        CellBuilder builder = new CellBuilder(5, 5, 0);
        Cell[][] board = builder.buildBoard();
        Assert.assertEquals(5, board.length);
        Assert.assertEquals(5, board[0].length);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                Assert.assertNotNull(board[i][j]);
            }
        }
    }

    @Test
    public void buildBoardWithDimensionFiveAnd2Mines() {
        CellBuilder builder = new CellBuilder(5, 5, 2);
        Cell[][] board = builder.buildBoard();
        Assert.assertEquals(5, board.length);
        Assert.assertEquals(5, board[0].length);
        int countOfMines = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                Assert.assertNotNull(board[i][j]);
                if (board[i][j].isMine()) {
                    countOfMines++;
                }
            }
        }
        Assert.assertEquals(2, countOfMines);
    }


}
