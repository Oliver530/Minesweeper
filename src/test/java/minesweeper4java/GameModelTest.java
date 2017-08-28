package minesweeper4java;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by olivergerhardt on 28.08.17.
 */
public class GameModelTest {

    @Test
    public void createGameModel() {
        GameModel game = new GameModel();
        Assert.assertEquals(GameState.READY, game.getState());
    }

    @Test
    public void startGame() {
        GameModel game = new GameModel();
        CellBuilder builder = new CellBuilder(4, 0);
        Board board = new Board(builder.buildBoard());
        game.startGame(board);
        Assert.assertEquals(GameState.RUNNING, game.getState());
    }

    @Test(expected = IllegalStateException.class)
    public void openCellBeforeGameIsRunning() {
        GameModel game = new GameModel();
        game.openCell(0, 0);
    }

    @Test
    public void openCellWhileGameIsRunning() {
        GameModel game = new GameModel();
        CellBuilder builder = new CellBuilder(4, 0);
        game.startGame(new Board(builder.buildBoard()));
        game.openCell(0, 0);
    }

    @Test
    public void getCellInfo() {
        GameModel game = new GameModel();
        CellBuilder builder = new CellBuilder(4, 0);
        game.startGame(new Board(builder.buildBoard()));
        CellInfo cellInfo = game.getCellInfo(0, 0);
        Assert.assertFalse(cellInfo.isVisited());
    }

    @Test
    public void openCellAndGetCellInfo() {
        GameModel game = new GameModel();
        CellBuilder builder = new CellBuilder(4, 0);
        game.startGame(new Board(builder.buildBoard()));
        game.openCell(0, 0);
        CellInfo cellInfo = game.getCellInfo(0, 0);
        Assert.assertTrue(cellInfo.isVisited());
    }

    @Test
    public void openMine() {
        GameModel game = new GameModel();
        CellBuilder builder = new CellBuilder(4, 4*4);
        game.startGame(new Board(builder.buildBoard()));
        game.openCell(0, 0);
        Assert.assertEquals(GameState.LOST, game.getState());
    }

    @Test
    public void openAllFreeFields() {
        GameModel game = new GameModel();
        CellBuilder builder = new CellBuilder(4, 0);
        game.startGame(new Board(builder.buildBoard()));
        for (int row = 0; row < game.getRowCount(); row++) {
            for (int col = 0; col < game.getColCount(); col++) {
                game.openCell(row, col);
            }
        }
        Assert.assertEquals(GameState.WON, game.getState());
    }

}
