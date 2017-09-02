package view.console.useraction;

import minesweeper4java.GameModel;
import view.console.ConsoleViewDrawer;

/**
 * Created by olivergerhardt on 02.09.17.
 */
public class UserActionInvalid extends UserAction {

    @Override
    public void perform(final GameModel gameModel, final ConsoleViewDrawer drawer) {
        // Ignore invalid user input
    }

}
