package View.Console.UserAction;

import model.GameModel;

/**
 * Created by olivergerhardt on 31.08.17.
 */
public class UserActionExit extends UserAction {

    @Override
    public void perform(GameModel gameModel) {
        System.exit(0);
    }
}
