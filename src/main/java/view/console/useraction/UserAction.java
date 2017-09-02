package view.console.useraction;

import minesweeper4java.GameModel;
import view.console.ConsoleViewDrawer;

/**
 * Created by olivergerhardt on 31.08.17.
 */
public abstract class UserAction {

    public abstract void perform(GameModel gameModel, ConsoleViewDrawer drawer);

}
