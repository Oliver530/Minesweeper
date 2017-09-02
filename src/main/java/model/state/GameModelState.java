package model.state;

import model.GameModelImpl;

/**
 * Created by olivergerhardt on 01.09.17.
 */
public interface GameModelState {

    void openCell(GameModelImpl context, int row, int col);

    void changeMarkedAsBomb(GameModelImpl context, int row, int col);

    void visitAllAndRemoveMarks(GameModelImpl context);

}
