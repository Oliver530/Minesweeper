package view.console.useraction;

import minesweeper.GameModel;
import view.console.ConsoleViewDrawer;

/**
 * Created by olivergerhardt on 31.08.17.
 */
public final class UserActionHelp extends UserAction {

    @Override
    public void perform(final GameModel gameModel, final ConsoleViewDrawer drawer) {
        System.out.println("The following commands are available:");
        System.out.println(" - open <row> <col>     Use to open an unvisited cell");
        System.out.println(" - o <row> <col>        Use to open an unvisited cell");
        System.out.println(" - mark <row> <col>     Use to (un)mark an unvisited cell as a mine");
        System.out.println(" - m <row> <col>     Use to (un)mark an unvisited cell as a mine");
        System.out.println(" - exit");
        System.out.println();
    }
}
