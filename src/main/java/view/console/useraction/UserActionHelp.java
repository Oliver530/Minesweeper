package View.Console.UserAction;

import View.Console.ConsoleViewDrawer;
import model.GameModel;

/**
 * Created by olivergerhardt on 31.08.17.
 */
public class UserActionHelp extends UserAction {
    @Override
    public void perform(GameModel gameModel, ConsoleViewDrawer drawer) {
        System.out.println("The following commands are available:");
        System.out.println(" - open <row> <col>     Use to open an unvisited cell");
        System.out.println(" - o <row> <col>        Use to open an unvisited cell");
        System.out.println(" - mark <row> <col>     Use to (un)mark an unvisited cell as a mine");
        System.out.println(" - m <row> <col>     Use to (un)mark an unvisited cell as a mine");
        System.out.println(" - exit");
        System.out.println();
    }
}
