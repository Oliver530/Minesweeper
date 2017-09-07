package view.console;

import minesweeper.GameInfoProvider;
import minesweeper.GameModel;
import model.cell.CellReadOnly;

/**
 * Created by olivergerhardt on 31.08.17.
 */
public final class ConsoleViewDrawer {

    private static final String ICON_NOT_VISITED = " · ";
    private static final String ICON_IS_MARKED_AS_BOMB = " @ ";
    private static final String ICON_IS_BOMB = " X ";

    private final GameModel gameModel;
    private final GameInfoProvider gameInfoProvider;


    public ConsoleViewDrawer(final GameModel gameModel) {
        this.gameModel = gameModel;
        this.gameInfoProvider = gameModel.getGameInfoProvider();
    }


    public void draw() {
        printHeader();

        for (int row = 0; row < gameInfoProvider.getRowCount(); row++) {
            String index = String.format("%1$2s", row);
            System.out.print(index + " |");
            for (int col = 0; col < gameInfoProvider.getColCount(); col++) {
                System.out.print(getIcon(row, col));
            }
            System.out.println();
        }
        System.out.println();
    }

    private String getIcon(final int row, final int col) {
        CellReadOnly cell = gameModel.getCell(row, col);
        if (cell.isMarkedAsMineByUser()) {
            return ICON_IS_MARKED_AS_BOMB;
        }
        if (!cell.isOpened()) {
            return ICON_NOT_VISITED;
        }
        if (cell.isMineInside()) {
            return ICON_IS_BOMB;
        }
        int neighbourCount = gameModel.getNeighbourMines(row, col);
        if (neighbourCount == 0) {
            return "   ";
        } else {
            return " " + neighbourCount + " ";
        }
    }

    private void printHeader() {
        String headerCols = "   |";
        String headerLine = "---|";
        for (int i = 0; i < gameInfoProvider.getColCount(); i++) {
            String index = String.format("%1$2s", i);
            headerCols = headerCols.concat(index + " ");
            headerLine = headerLine.concat("---");
        }
        System.out.println(headerCols);
        System.out.println(headerLine);
    }

}
