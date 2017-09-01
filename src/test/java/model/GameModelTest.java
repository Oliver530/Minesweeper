package model;

import model.cell.Cell;
import model.cell.CellBuilder;
import model.cell.CellRO;
import model.state.*;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by olivergerhardt on 28.08.17.
 */
public class GameModelTest {

    @Test
    public void createGameModel() {
        GameModel game = new GameModel();
        Assert.assertTrue(game.getState() instanceof GameStateMissingBoard);
    }

    @Test
    public void startGame() {
        GameModel game = new GameModel();
        game.startGame(new Board(4, GameDifficulty.PEACE));
        Assert.assertTrue(game.getState() instanceof GameStateReady);
    }

    @Test
    public void openCellBeforeGameIsRunning() {
        GameModel game = new GameModel();
        game.openCell(0, 0);
    }

    @Test
    public void openCellWhileGameIsRunning() {
        GameModel game = new GameModel();
        game.startGame(new Board(4, GameDifficulty.PEACE));
        game.openCell(0, 0);
    }

    @Test
    public void getCellInfo() {
        GameModel game = new GameModel();
        game.startGame(new Board(4, GameDifficulty.PEACE));
        CellRO cell = game.getCell(0, 0);
        Assert.assertFalse(cell.isVisited());
    }

    @Test
    public void openCellAndGetCellInfo() {
        GameModel game = new GameModel();
        game.startGame(new Board(4, GameDifficulty.PEACE));
        game.openCell(0, 0);
        CellRO cell = game.getCell(0, 0);
        Assert.assertTrue(cell.isVisited());
    }

    @Test
    public void openAllFreeFields() {
        GameModel game = new GameModel();
        game.startGame(new Board(4, GameDifficulty.PEACE));
        game.openCell(0,0);
        Assert.assertTrue(game.getState() instanceof GameStateWon);
    }

    @Test
    public void getCountOfMinesOnEasyGameAndDimension10() {
        GameModel game = new GameModel();
        game.startGame(new Board(10, GameDifficulty.EASY));
        Assert.assertEquals(10, game.getCountOfMines());
    }

    @Test
    public void getCountOfMinesOnEasyGameAndDimension5() {
        GameModel game = new GameModel();
        game.startGame(new Board(5, GameDifficulty.EASY));
        Assert.assertEquals(2, game.getCountOfMines());
    }

    @Test
    public void getCountOfMinesOnMediumGameAndDimension10() {
        GameModel game = new GameModel();
        game.startGame(new Board(10, GameDifficulty.MEDIUM));
        Assert.assertEquals(20, game.getCountOfMines());
    }

    @Test
    public void getCountOfMinesOnMediumGameAndDimension5() {
        GameModel game = new GameModel();
        game.startGame(new Board(5, GameDifficulty.MEDIUM));
        Assert.assertEquals(5, game.getCountOfMines());
    }

    @Test
    public void getCountOfMinesOnHardGameAndDimension10() {
        GameModel game = new GameModel();
        game.startGame(new Board(10, GameDifficulty.HARD));
        Assert.assertEquals(30, game.getCountOfMines());
    }

    @Test
    public void getCountOfMinesOnHardGameAndDimension5() {
        GameModel game = new GameModel();
        game.startGame(new Board(5, GameDifficulty.HARD));
        Assert.assertEquals(7, game.getCountOfMines());
    }

    @Test
    public void markCellAsBomb() {
        GameModel game = new GameModel();
        game.startGame(new Board(5, GameDifficulty.EASY));

        CellRO cell = game.getCell(0,0);
        Assert.assertFalse(cell.isMarkedAsBomb());
        game.changeMarkedAsBomb(0,0);
        Assert.assertTrue(cell.isMarkedAsBomb());
    }

    @Test
    public void visitAllAndRemoveMarks() {
        GameModel game = new GameModel();
        game.startGame(new Board(5, GameDifficulty.EASY));
        game.changeMarkedAsBomb(0, 0);
        game.setState(new GameStateLost());

        game.visitAllAndRemoveMarks();
        Assert.assertFalse(game.getCell(0, 0).isMarkedAsBomb());
        for (int row = 0; row < game.getRowCount(); row++) {
            for (int col = 0; col < game.getColCount(); col++) {
                Assert.assertTrue(game.getCell(row, col).isVisited());
            }
        }
    }

    @Test
    public void openVisitedCell() {
        CellBuilder builder = new CellBuilder(5, 0);
        Cell[][] fields = builder.buildBoard();
        fields[0][1].changeMarkedAsBomb();
        fields[0][1].setMine();
        fields[1][1].changeMarkedAsBomb();
        fields[1][1].setMine();
        fields[2][1].changeMarkedAsBomb();
        fields[2][1].setMine();
        fields[2][0].changeMarkedAsBomb();
        fields[2][0].setMine();

        GameModel game = new GameModel();
        game.debug(fields, 4);

        game.openCell(0, 0);
        Assert.assertFalse(game.getCell(1, 0).isVisited());
        game.openCell(0, 0);
        Assert.assertTrue(game.getCell(1, 0).isVisited());
    }

    @Test
    public void firstHitIsAMine() {
        CellBuilder builder = new CellBuilder(5, 0);
        Cell[][] fields = builder.buildBoard();
        fields[0][0].setMine();
        fields[2][2].setMine();

        GameModel game = new GameModel();
        game.debug(fields, 1);
        CellRO cell = game.getCell(0, 0);

        Assert.assertTrue(game.getState() instanceof GameStateReady);
        Assert.assertTrue(cell.isMine());
        game.openCell(0, 0);
        Assert.assertTrue(game.getState() instanceof GameStateRunning);
        Assert.assertFalse(cell.isMine());
    }

}
