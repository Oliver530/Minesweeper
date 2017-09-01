package view.console.useraction;

import minesweeper4java.MinesweeperGameModel;
import view.console.ConsoleViewDrawer;

/**
 * Created by olivergerhardt on 31.08.17.
 */
public class UserActionExit extends UserAction {

    @Override
    public void perform(MinesweeperGameModel gameModel, ConsoleViewDrawer drawer) {
        System.exit(0);
    }
}
