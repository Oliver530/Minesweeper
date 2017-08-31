package View.Console;

import model.Cell.CellInfo;
import model.GameModel;

/**
 * Created by olivergerhardt on 31.08.17.
 */
class ConsoleViewDrawer {

    private final static String ICON_NOT_VISITED = " · ";
    private final static String ICON_IS_MARKED_AS_BOMB = " @ ";
    private final static String ICON_IS_BOMB = " X ";

    private final GameModel gameModel;

    public ConsoleViewDrawer(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    public void draw() {
        printHeader();

        for (int row = 0; row < gameModel.getRowCount(); row++) {
            String index = String.format("%1$2s", row);
            System.out.print(index + " |");
            for (int col = 0; col < gameModel.getColCount(); col++) {
                CellInfo cellInfo = gameModel.getCellInfo(row, col);
                System.out.print(getIcon(cellInfo));
            }
            System.out.println();
        }
        System.out.println();
    }

    private String getIcon(CellInfo cellInfo) {
        if (cellInfo.isMarkedAsBomb()) {
            return ICON_IS_MARKED_AS_BOMB;
        }
        if (!cellInfo.isVisited()) {
            return ICON_NOT_VISITED;
        }
        if (cellInfo.isMine()) {
            return ICON_IS_BOMB;
        }
        int neighbourCount = cellInfo.getCountOfNeighbourMines();
        if (neighbourCount == 0) {
            return "   ";
        } else {
            return " " + neighbourCount + " ";
        }
    }

    private void printHeader() {
        String headerCols = "   |";
        String headerLine = "---|";
        for (int i = 0; i < gameModel.getColCount(); i++) {
            String index = String.format("%1$2s", i);
            headerCols = headerCols.concat(index + " ");
            headerLine = headerLine.concat("---");
        }
        System.out.println(headerCols);
        System.out.println(headerLine);
    }
}
