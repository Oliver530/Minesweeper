package minesweeper4java;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by olivergerhardt on 28.08.17.
 */
public class NullCellTest {

    @Test
    public void notPossibleToVisitNullCells() {
        Cell cell = NullCell.getInstance();
        Assert.assertFalse(cell.isVisited());
        cell.visit();
        Assert.assertFalse(cell.isVisited());
    }

    @Test
    public void notPossibleToMineNullCells() {
        Cell cell = NullCell.getInstance();
        Assert.assertFalse(cell.isMine());
        cell.setMine();
        Assert.assertFalse(cell.isMine());
    }
}
