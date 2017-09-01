package View.Console.UserAction;

import View.Console.ConsoleViewDrawer;
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
