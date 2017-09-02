package model.state;

import model.GameModelImpl;
import model.cell.Cell;

/**
 * Created by olivergerhardt on 01.09.17.
 */
public final class GameStateLost implements GameModelState {

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
        int rowCount = context.getBoard().getRowCount();
        int colCount = context.getBoard().getColCount();

        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                Cell cell = context.getBoard().getCell(row, col);
                visitAndRemoveMark(cell);
            }
        }
    }

    private void visitAndRemoveMark(final Cell cell) {
        if (!cell.isVisited()) {
            cell.visit();
        }
        if (cell.isMarkedAsBomb()) {
            cell.changeMarkedAsBomb();
        }
    }

}
