package model.state;

import model.GameModelImpl;
import model.cell.Cell;

/**
 * Created by olivergerhardt on 01.09.17.
 */
public class GameStateReady extends GameStateRunning {

    @Override
    public void openCell(GameModelImpl context, int row, int col) {
        if (context.getCell(row, col).isMine()) {
            context.getBoard().moveMineToRandomCell(row, col);
        }
        context.setState(new GameStateRunning());
        super.openCell(context, row, col);
    }

}
