package model.state;

import model.GameModel;

/**
 * Created by olivergerhardt on 01.09.17.
 */
public interface GameModelState {

    void openCell(GameModel context, int row, int col);

    void changeMarkedAsBomb(GameModel context, int row, int col);

    void visitAllAndRemoveMarks(GameModel context);

}
