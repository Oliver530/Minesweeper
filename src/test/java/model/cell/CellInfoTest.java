package model.Cell;

import model.Cell.Cell;
import model.Cell.CellInfo;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by olivergerhardt on 28.08.17.
 */
public class CellInfoTest {

    @Test
    public void createNotVisitedCellInfo() {
        Cell cell = new Cell();
        CellInfo cellInfo = new CellInfo(cell);
        Assert.assertFalse(cellInfo.isVisited());
    }

    @Test
    public void createVisitedCellInfo() {
        Cell cell = new Cell();
        cell.visit();
        CellInfo cellInfo = new CellInfo(cell);
        Assert.assertTrue(cellInfo.isVisited());
    }

    @Test
    public void visitCellAfterwards() {
        Cell cell = new Cell();
        CellInfo cellInfo = new CellInfo(cell);
        Assert.assertFalse(cellInfo.isVisited());
        cell.visit();
        Assert.assertTrue(cellInfo.isVisited());
    }

    @Test
    public void getCountOfNeighbourMinesZero() {
        Cell cell = new Cell();
        CellInfo cellInfo = new CellInfo(cell, 0);
        Assert.assertEquals(0, cellInfo.getCountOfNeighbourMines());
    }

    @Test
    public void getCountOfNeighbourMinesThree() {
        Cell cell = new Cell();
        CellInfo cellInfo = new CellInfo(cell, 3);
        Assert.assertEquals(3, cellInfo.getCountOfNeighbourMines());
    }
}
