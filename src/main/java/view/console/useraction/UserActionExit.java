package view.console.useraction;

import view.console.ConsoleViewDrawer;
import model.GameModel;

/**
 * Created by olivergerhardt on 31.08.17.
 */
public class UserActionExit extends UserAction {

    @Override
    public void perform(GameModel gameModel, ConsoleViewDrawer drawer) {
        System.exit(0);
    }
}
