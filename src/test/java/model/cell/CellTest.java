package model.cell;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by olivergerhardt on 27.08.17.
 */
public class CellTest {

    @Test
    public void createNewCell() {
        Cell cell = new Cell();
        Assert.assertFalse(cell.isVisited());
        Assert.assertFalse(cell.isMine());
    }

    @Test
    public void visitCellOnce() {
        Cell cell = new Cell();
        Assert.assertFalse(cell.isVisited());
        cell.visit();
        Assert.assertTrue(cell.isVisited());
    }

    @Test(expected = IllegalStateException.class)
    public void visitCellTwice() {
        Cell cell = new Cell();
        Assert.assertFalse(cell.isVisited());
        cell.visit();
        Assert.assertTrue(cell.isVisited());
        cell.visit();
    }

    @Test
    public void setMineIntoCellOnce() {
        Cell cell = new Cell();
        Assert.assertFalse(cell.isMine());
        cell.setMine();
        Assert.assertTrue(cell.isMine());
    }

}
