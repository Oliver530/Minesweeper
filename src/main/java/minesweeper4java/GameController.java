package minesweeper4java;

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
