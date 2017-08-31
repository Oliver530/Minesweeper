package minesweeper4java;

import View.Console.ConsoleView;
import model.GameModel;

/**
 * Created by olivergerhardt on 31.08.17.
 */
public class GameController {

    private GameModel gameModel;
    private MinesweeperView view;

    public void start() {
        gameModel = new GameModel();
        view = new ConsoleView(gameModel);
        view.play();
    }

}
