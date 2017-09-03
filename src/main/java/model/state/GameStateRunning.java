package model.state;

import model.GameModelImpl;
import model.cell.Cell;

import java.util.List;

/**
 * Created by olivergerhardt on 01.09.17.
 */
public class GameStateRunning implements GameModelState {

    @Override
    public void openCell(final GameModelImpl context, final int row, final int col) {
        Cell cell = context.getCell(row, col);
        if (cell.isMine()) {
            context.setState(new GameStateLost());
            return;
        }

        if (cell.isVisited() || context.getBoard().getNeighbourMineCount(cell) == 0) {
            openCellR(context, cell);
        } else {
            cell.visit();
        }

        if (gameIsWon(context)) {
            context.setState(new GameStateWon());
        }
    }

    @Override
    public void changeMarkedAsBomb(final GameModelImpl context, final int row, final int col) {
        Cell cell = context.getCell(row, col);
        cell.changeMarkedAsBomb();
    }

    @Override
    public void visitAllAndRemoveMarks(final GameModelImpl context) {
        // Operation not allowed in this state!
    }

    private void openCellR(final GameModelImpl context, final Cell cell) {
        List<Cell> neighbours = context.getBoard().getNeighbourCells(cell);
        for (Cell neighbour : neighbours) {
            if (neighbour.isVisited()) {
                continue;
            }

            if (cell.isMine() && !cell.isMarkedAsBomb()) {
                context.setState(new GameStateLost());
                return;
            } else {
                neighbour.visit();

                if (context.getBoard().getNeighbourMineCount(neighbour) == 0) {
                    openCellR(context, neighbour);
                }
            }
        }
    }

    private boolean gameIsWon(final GameModelImpl context) {
        for (int row = 0; row < context.getBoard().getRows(); row++) {
            for (int col = 0; col < context.getBoard().getColumns(); col++) {
                if (!context.getBoard().getCell(row, col).isMine() && !context.getBoard().getCell(row, col).isVisited()) {
                    return false;
                }
            }
        }
        return true;
    }
}
