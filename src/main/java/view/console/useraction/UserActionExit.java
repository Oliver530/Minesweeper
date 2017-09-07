package view.console.useraction;

import minesweeper.GameModel;
import view.console.ConsoleViewDrawer;

/**
 * Created by olivergerhardt on 31.08.17.
 */
public final class UserActionExit extends UserAction {

    @Override
    public void perform(final GameModel gameModel, final ConsoleViewDrawer drawer) {
        System.exit(0);
    }
}
