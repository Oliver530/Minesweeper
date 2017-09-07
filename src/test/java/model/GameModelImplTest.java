package model;

import model.cell.Cell;
import model.cell.CellReadOnly;
import model.state.*;
import org.junit.Assert;
import org.junit.Test;
import util.GameDifficulty;

/**
 * Created by olivergerhardt on 28.08.17.
 */
public class GameModelImplTest {

    @Test
    public void createGameModel() {
        GameModelImpl game = new GameModelImpl();
        Assert.assertTrue(game.getState() instanceof GameStateReady);
        Assert.assertEquals(Board.ROWS_MINIMUM, game.getRowCount());
        Assert.assertEquals(Board.COLUMNS_MINIMUM, game.getColCount());
    }

    @Test
    public void startGame() {
        GameModelImpl game = new GameModelImpl();
        game.setBoard(new Board(4, 4, GameDifficulty.PEACE));
        Assert.assertTrue(game.getState() instanceof GameStateReady);
    }

    @Test
    public void openCellBeforeGameIsRunning() {
        GameModelImpl game = new GameModelImpl();
        game.openCell(0, 0);
    }

    @Test
    public void openCellWhileGameIsRunning() {
        GameModelImpl game = new GameModelImpl();
        game.setBoard(new Board(4, 4, GameDifficulty.PEACE));
        game.openCell(0, 0);
    }

    @Test
    public void getCellRO() {
        GameModelImpl game = new GameModelImpl();
        game.setBoard(new Board(4, 4, GameDifficulty.PEACE));
        CellReadOnly cell = game.getCell(0, 0);
        Assert.assertFalse(cell.isOpened());
    }

    @Test
    public void openCellAndGetCellRO() {
        GameModelImpl game = new GameModelImpl();
        game.setBoard(new Board(4, 4, GameDifficulty.PEACE));
        game.openCell(0, 0);
        CellReadOnly cell = game.getCell(0, 0);
        Assert.assertTrue(cell.isOpened());
    }

    @Test
    public void openAllFreeFields() {
        GameModelImpl game = new GameModelImpl();
        game.setBoard(new Board(4, 4, GameDifficulty.PEACE));
        game.openCell(0,0);
        Assert.assertTrue(game.getState() instanceof GameStateWon);
    }

    @Test
    public void getCountOfMinesOnEasyGameAndDimension10() {
        GameModelImpl game = new GameModelImpl();
        game.setBoard(new Board(10, 10, GameDifficulty.EASY));
        Assert.assertEquals(10, game.getCountOfMines());
    }

    @Test
    public void getCountOfMinesOnEasyGameAndDimension5() {
        GameModelImpl game = new GameModelImpl();
        game.setBoard(new Board(5, 5, GameDifficulty.EASY));
        Assert.assertEquals(2, game.getCountOfMines());
    }

    @Test
    public void getCountOfMinesOnMediumGameAndDimension10() {
        GameModelImpl game = new GameModelImpl();
        game.setBoard(new Board(10, 10, GameDifficulty.MEDIUM));
        Assert.assertEquals(20, game.getCountOfMines());
    }

    @Test
    public void getCountOfMinesOnMediumGameAndDimension5() {
        GameModelImpl game = new GameModelImpl();
        game.setBoard(new Board(5, 5, GameDifficulty.MEDIUM));
        Assert.assertEquals(5, game.getCountOfMines());
    }

    @Test
    public void getCountOfMinesOnHardGameAndDimension10() {
        GameModelImpl game = new GameModelImpl();
        game.setBoard(new Board(10, 10, GameDifficulty.HARD));
        Assert.assertEquals(30, game.getCountOfMines());
    }

    @Test
    public void getCountOfMinesOnHardGameAndDimension5() {
        GameModelImpl game = new GameModelImpl();
        game.setBoard(new Board(5, 5, GameDifficulty.HARD));
        Assert.assertEquals(7, game.getCountOfMines());
    }

    @Test
    public void markCellAsBomb() {
        GameModelImpl game = new GameModelImpl();
        game.setBoard(new Board(5, 5, GameDifficulty.EASY));

        CellReadOnly cell = game.getCell(0,0);
        Assert.assertFalse(cell.isMarkedAsMineByUser());
        game.changeMarkedAsBomb(0,0);
        Assert.assertTrue(cell.isMarkedAsMineByUser());
    }

    @Test
    public void visitAllAndRemoveMarks() {
        GameModelImpl game = new GameModelImpl();
        game.setBoard(new Board(5, 5, GameDifficulty.EASY));
        game.changeMarkedAsBomb(0, 0);
        game.setState(new GameStateLost());

        game.visitAllAndRemoveMarks();
        Assert.assertFalse(game.getCell(0, 0).isMarkedAsMineByUser());
        for (int row = 0; row < game.getRowCount(); row++) {
            for (int col = 0; col < game.getColCount(); col++) {
                Assert.assertTrue(game.getCell(row, col).isOpened());
            }
        }
    }

    @Test
    public void openVisitedCell() {
        Cell[][] fields = BoardBuilder.buildBoard(5, 5, 0);
        fields[0][1].changeMarkedAsBomb();
        fields[0][1].insertMine();
        fields[1][1].changeMarkedAsBomb();
        fields[1][1].insertMine();
        fields[2][1].changeMarkedAsBomb();
        fields[2][1].insertMine();
        fields[2][0].changeMarkedAsBomb();
        fields[2][0].insertMine();

        GameModelImpl game = new GameModelImpl();
        game.debug(fields, 4);

        game.openCell(0, 0);
        Assert.assertFalse(game.getCell(1, 0).isOpened());
        game.openCell(0, 0);
        Assert.assertTrue(game.getCell(1, 0).isOpened());
    }

    @Test
    public void firstHitIsAMine() {
        Cell[][] fields = BoardBuilder.buildBoard(5, 5, 0);
        fields[0][0].insertMine();
        fields[2][2].insertMine();

        GameModelImpl game = new GameModelImpl();
        game.debug(fields, 1);
        CellReadOnly cell = game.getCell(0, 0);

        Assert.assertTrue(game.getState() instanceof GameStateReady);
        Assert.assertTrue(cell.isMineInside());
        game.openCell(0, 0);
        Assert.assertTrue(game.getState() instanceof GameStateRunning);
        Assert.assertFalse(cell.isMineInside());
    }

}
