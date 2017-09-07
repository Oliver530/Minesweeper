package view.gui;

import minesweeper.GameModel;
import minesweeper.View;

/**
 * Created by olivergerhardt on 31.08.17.
 */
public class ViewGUI implements View {

    private final GameModel gameModel;

    public ViewGUI(final GameModel gameModel) {
        this.gameModel = gameModel;
    }

    @Override
    public void play() {
        // ToDo
    }
}
