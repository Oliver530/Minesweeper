package view.console.useraction;

import minesweeper4java.GameModel;
import view.console.ConsoleViewDrawer;

/**
 * Created by olivergerhardt on 31.08.17.
 */
public final class UserActionOpen extends UserAction {

    private final int row;
    private final int col;


    public UserActionOpen(final int row, final int col) {
        this.row = row;
        this.col = col;
    }


    @Override
    public void perform(final GameModel gameModel, final ConsoleViewDrawer drawer) {
        gameModel.openCell(row, col);

        if (!gameModel.gameLost() && !gameModel.gameWon()) {
            System.out.println();
            drawer.draw();
        }
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
