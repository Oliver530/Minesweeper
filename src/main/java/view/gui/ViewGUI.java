package view.gui;

import minesweeper4java.GameModel;
import minesweeper4java.View;

/**
 * Created by olivergerhardt on 31.08.17.
 */
public class ViewGUI implements View {

    private final GameModel gameModel;

    public ViewGUI(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    @Override
    public void play() {
        // ToDo
    }
}
