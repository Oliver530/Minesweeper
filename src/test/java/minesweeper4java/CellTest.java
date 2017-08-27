package minesweeper4java;

import minesweeper4java.Cell;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by olivergerhardt on 27.08.17.
 */
public class CellTest {

    @Test
    public void createNewCell() {
        Cell cell = new Cell();
        Assert.assertEquals(cell.isVisited(), false);
        Assert.assertEquals(cell.isMine(), false);
    }

    @Test
    public void visitCellOnce() {
        Cell cell = new Cell();
        Assert.assertEquals(cell.isVisited(), false);
        cell.visit();
        Assert.assertEquals(cell.isVisited(), true);
    }

    @Test(expected = IllegalStateException.class)
    public void visitCellTwice() {
        Cell cell = new Cell();
        Assert.assertEquals(cell.isVisited(), false);
        cell.visit();
        Assert.assertEquals(cell.isVisited(), true);
        cell.visit();
    }

    @Test
    public void setMineIntoCellOnce() {
        Cell cell = new Cell();
        Assert.assertEquals(cell.isMine(), false);
        boolean mined = cell.setMine();
        Assert.assertEquals(mined, true);
        Assert.assertEquals(cell.isMine(), true);
    }

    @Test
    public void setMineIntoCellTwice() {
        Cell cell = new Cell();
        Assert.assertEquals(cell.isMine(), false);
        boolean mined = cell.setMine();
        Assert.assertEquals(mined, true);
        mined = cell.setMine();
        Assert.assertEquals(mined, false);
    }
}
