package view.console.useraction;

import minesweeper4java.MinesweeperGameModel;
import view.console.ConsoleViewDrawer;

/**
 * Created by olivergerhardt on 31.08.17.
 */
public abstract class UserAction {

    public abstract void perform(MinesweeperGameModel gameModel, ConsoleViewDrawer drawer);

}
