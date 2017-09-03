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
        Assert.assertFalse(cell.isOpened());
        Assert.assertFalse(cell.isMineInside());
    }

    @Test
    public void openCellOnce() {
        Cell cell = new Cell();
        Assert.assertFalse(cell.isOpened());
        cell.open();
        Assert.assertTrue(cell.isOpened());
    }

    @Test(expected = IllegalStateException.class)
    public void openCellTwice() {
        Cell cell = new Cell();
        Assert.assertFalse(cell.isOpened());
        cell.open();
        Assert.assertTrue(cell.isOpened());
        cell.open();
    }

    @Test
    public void setMineIntoCell() {
        Cell cell = new Cell();
        Assert.assertFalse(cell.isMineInside());
        cell.insertMine();
        Assert.assertTrue(cell.isMineInside());
    }

}
