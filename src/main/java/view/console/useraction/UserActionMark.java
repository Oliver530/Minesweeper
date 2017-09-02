package view.console.useraction;

import minesweeper4java.GameModel;
import view.console.ConsoleViewDrawer;

/**
 * Created by olivergerhardt on 31.08.17.
 */
public class UserActionMark extends UserAction {

    private final int row;
    private final int col;

    public UserActionMark(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public void perform(GameModel gameModel, ConsoleViewDrawer drawer) {
        gameModel.changeMarkedAsBomb(row, col);
        System.out.println();
        drawer.draw();
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
