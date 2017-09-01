package view.console.useraction;

import view.console.ConsoleViewDrawer;
import model.GameModel;

/**
 * Created by olivergerhardt on 31.08.17.
 */
public abstract class UserAction {

    public abstract void perform(GameModel gameModel, ConsoleViewDrawer drawer);

}
