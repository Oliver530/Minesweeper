package model.state;

import model.GameModel;
import model.cell.Cell;

/**
 * Created by olivergerhardt on 01.09.17.
 */
public class GameStateReady extends GameStateRunning {

    @Override
    public void openCell(GameModel context, int row, int col) {
        Cell cell = context.getCell(row, col);

        if (cell.isMine()) {
            context.getBoard().moveMineToRandomCell(row, col);
        }
        context.setState(new GameStateRunning());

        super.openCell(context, row, col);
    }

}
