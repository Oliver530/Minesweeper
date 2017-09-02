package model.state;

import model.GameModelImpl;

/**
 * Created by olivergerhardt on 01.09.17.
 */
public class GameStateWon implements GameModelState {

    @Override
    public void openCell(GameModelImpl context, int row, int col) {
        // Operation not allowed in this state!
    }

    @Override
    public void changeMarkedAsBomb(GameModelImpl context, int row, int col) {
        // Operation not allowed in this state!
    }

    @Override
    public void visitAllAndRemoveMarks(GameModelImpl context) {
        // Operation not allowed in this state!
    }
}
