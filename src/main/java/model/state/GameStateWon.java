package model.state;

import model.GameModelImpl;

/**
 * Created by olivergerhardt on 01.09.17.
 */
public final class GameStateWon implements GameModelState {

    @Override
    public void openCell(final GameModelImpl context, final int row, final int col) {
        // Operation not allowed in this state!
    }

    @Override
    public void changeMarkedAsBomb(final GameModelImpl context, final int row, final int col) {
        // Operation not allowed in this state!
    }

    @Override
    public void visitAllAndRemoveMarks(final GameModelImpl context) {
        // Operation not allowed in this state!
    }
}
