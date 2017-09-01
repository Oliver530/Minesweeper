package minesweeper4java;

import model.Board;
import model.GameDifficulty;
import model.cell.CellBuilder;
import view.console.ConsoleView;
import model.GameModel;

/**
 * Created by olivergerhardt on 31.08.17.
 */
class GameController {

    public void start() {
        MinesweeperGameModel gameModel = new GameModel();
        MinesweeperView view = new ConsoleView(gameModel);
        view.play();
    }

}
