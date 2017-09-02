package minesweeper4java;

import model.GameModel;
import view.console.ConsoleView;

/**
 * Created by olivergerhardt on 31.08.17.
 */
class Minesweeper4Java {

    public static void main(String[] args) {
        MinesweeperGameModel gameModel = new GameModel();
        MinesweeperView view = new ConsoleView(gameModel);
        view.play();
    }

}
