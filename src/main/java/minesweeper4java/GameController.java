package minesweeper4java;

import View.Console.ConsoleView;
import model.GameModel;

/**
 * Created by olivergerhardt on 31.08.17.
 */
class GameController {

    public void start() {
        GameModel gameModel = new GameModel();
        MinesweeperView view = new ConsoleView(gameModel);
        view.play();
    }

}
