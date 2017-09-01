package model.state;

import model.GameModel;

/**
 * Created by olivergerhardt on 01.09.17.
 */
public class GameStateWon implements GameModelState {

    @Override
    public void openCell(GameModel context, int row, int col) {}

    @Override
    public void changeMarkedAsBomb(GameModel context, int row, int col) {}

    @Override
    public void visitAllAndRemoveMarks(GameModel context) {}
}
