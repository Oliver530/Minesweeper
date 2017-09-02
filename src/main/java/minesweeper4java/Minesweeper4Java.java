package minesweeper4java;

import model.GameModelImpl;
import view.console.ConsoleView;
import view.gui.ViewGUI;

/**
 * Created by olivergerhardt on 31.08.17.
 */
class Minesweeper4Java {

    public static void main(String[] args) {

        // Choose business logic implementation
        GameModel gameModel = new GameModelImpl();

        // Choose view implementation
        View view = getView(gameModel, true);
        view.play();

    }

    private static View getView(final GameModel gameModel, final boolean noGUI) {
        if (noGUI) {
            return new ConsoleView(gameModel);
        } else {
            // ToDo to be implemented...
            return new ViewGUI(gameModel);
        }
    }

}
