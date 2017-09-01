package model.state;

import model.GameModel;
import model.cell.Cell;

import java.util.List;

/**
 * Created by olivergerhardt on 01.09.17.
 */
public class GameStateRunning implements GameModelState {

    @Override
    public void openCell(GameModel context, int row, int col) {
        Cell cell = context.getCell(row, col);

        if (cell.isMine()) {
            context.setState(new GameStateLost());
            return;
        }

        if (cell.isVisited() || context.getBoard().getCountOfNeighbourMines(cell) == 0) {
            openCellR(context, cell);
        } else {
            cell.visit();
        }

        if (checkWinCondition(context)) {
            context.setState(new GameStateWon());
        }
    }

    @Override
    public void changeMarkedAsBomb(GameModel context, int row, int col) {
        Cell cell = context.getCell(row, col);
        cell.changeMarkedAsBomb();
    }

    @Override
    public void visitAllAndRemoveMarks(GameModel context) {}

    private void openCellR(GameModel context, Cell cell) {
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

                if (context.getBoard().getCountOfNeighbourMines(neighbour) == 0) {
                    openCellR(context, neighbour);
                }
            }
        }
    }

    private boolean checkWinCondition(GameModel context) {
        for (int row = 0; row < context.getBoard().getRowCount(); row++) {
            for (int col = 0; col < context.getBoard().getColCount(); col++) {
                if (!context.getBoard().getCell(row, col).isMine() && !context.getBoard().getCell(row, col).isVisited()) {
                    return false;
                }
            }
        }
        return true;
    }
}
