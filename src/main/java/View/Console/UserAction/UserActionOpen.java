package View.Console.UserAction;

import model.GameModel;

/**
 * Created by olivergerhardt on 31.08.17.
 */
public class UserActionOpen extends UserAction {

    private final int row;
    private final int col;

    public UserActionOpen(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public void perform(GameModel gameModel) {
        gameModel.openCell(row, col);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
