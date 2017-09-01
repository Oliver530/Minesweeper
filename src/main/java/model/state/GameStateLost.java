package model.state;

import model.GameModel;
import model.cell.Cell;

/**
 * Created by olivergerhardt on 01.09.17.
 */
public class GameStateLost implements GameModelState {

    @Override
    public void openCell(GameModel context, int row, int col) {}

    @Override
    public void changeMarkedAsBomb(GameModel context, int row, int col) {}

    @Override
    public void visitAllAndRemoveMarks(GameModel context) {
        for (int row = 0; row < context.getBoard().getRowCount(); row++) {
            for (int col = 0; col < context.getBoard().getColCount(); col++) {
                Cell cell = context.getBoard().getCell(row, col);
                if (!cell.isVisited()) {
                    cell.visit();
                }
                if (cell.isMarkedAsBomb()) {
                    cell.changeMarkedAsBomb();
                }
            }
        }
    }

}
