package model.state;

import model.GameModelImpl;

/**
 * Created by olivergerhardt on 01.09.17.
 */
public final class GameStateReady extends GameStateRunning {

    @Override
    public void openCell(final GameModelImpl context, final int row, final int col) {
        if (context.getCell(row, col).isMineInside()) {
            context.getBoard().moveMineToRandomCell(row, col);
        }
        context.setState(new GameStateRunning());
        super.openCell(context, row, col);
    }

}
