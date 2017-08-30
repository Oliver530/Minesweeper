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
        game.startGame(4, GameDifficulty.PEACE);
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
        game.startGame(4, GameDifficulty.PEACE);
        game.openCell(0, 0);
    }

    @Test
    public void getCellInfo() {
        GameModel game = new GameModel();
        game.startGame(4, GameDifficulty.PEACE);
        CellInfo cellInfo = game.getCellInfo(0, 0);
        Assert.assertFalse(cellInfo.isVisited());
    }

    @Test
    public void openCellAndGetCellInfo() {
        GameModel game = new GameModel();
        game.startGame(4, GameDifficulty.PEACE);
        game.openCell(0, 0);
        CellInfo cellInfo = game.getCellInfo(0, 0);
        Assert.assertTrue(cellInfo.isVisited());
    }

    @Test
    public void openMine() {
        GameModel game = new GameModel();
        game.startGame(4, GameDifficulty.HELL);
        game.openCell(0, 0);
        Assert.assertEquals(GameState.LOST, game.getState());
    }

    @Test
    public void openAllFreeFields() {
        GameModel game = new GameModel();
        CellBuilder builder = new CellBuilder(4, 0);
        game.startGame(4, GameDifficulty.PEACE);
        for (int row = 0; row < game.getRowCount(); row++) {
            for (int col = 0; col < game.getColCount(); col++) {
                game.openCell(row, col);
            }
        }
        Assert.assertEquals(GameState.WON, game.getState());
    }

    @Test
    public void getCountOfMinesOnEasyGameAndDimension10() {
        GameModel game = new GameModel();
        game.startGame(10, GameDifficulty.EASY);
        Assert.assertEquals(10, game.getCountOfMines());
    }

    @Test
    public void getCountOfMinesOnEasyGameAndDimension5() {
        GameModel game = new GameModel();
        game.startGame(5, GameDifficulty.EASY);
        Assert.assertEquals(2, game.getCountOfMines());
    }

    @Test
    public void getCountOfMinesOnMediumGameAndDimension10() {
        GameModel game = new GameModel();
        game.startGame(10, GameDifficulty.MEDIUM);
        Assert.assertEquals(20, game.getCountOfMines());
    }

    @Test
    public void getCountOfMinesOnMediumGameAndDimension5() {
        GameModel game = new GameModel();
        game.startGame(5, GameDifficulty.MEDIUM);
        Assert.assertEquals(5, game.getCountOfMines());
    }

    @Test
    public void getCountOfMinesOnHardGameAndDimension10() {
        GameModel game = new GameModel();
        game.startGame(10, GameDifficulty.HARD);
        Assert.assertEquals(30, game.getCountOfMines());
    }

    @Test
    public void getCountOfMinesOnHardGameAndDimension5() {
        GameModel game = new GameModel();
        game.startGame(5, GameDifficulty.HARD);
        Assert.assertEquals(7, game.getCountOfMines());
    }

}
