package model.state;

import model.GameModelImpl;

/**
 * Created by olivergerhardt on 01.09.17.
 */
public class GameStateWon implements GameModelState {

    @Override
    public void openCell(GameModelImpl context, int row, int col) {}

    @Override
    public void changeMarkedAsBomb(GameModelImpl context, int row, int col) {}

    @Override
    public void visitAllAndRemoveMarks(GameModelImpl context) {}
}
