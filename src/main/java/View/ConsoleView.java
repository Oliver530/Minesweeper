package View;

import minesweeper4java.CellInfo;
import minesweeper4java.GameDifficulty;
import minesweeper4java.GameModel;

import java.util.Scanner;


/**
 * Created by olivergerhardt on 30.08.17.
 */
public class ConsoleView {

    private final GameModel gameModel;
    private final Scanner keyboard;
    private final CommandLineUserActionPicker picker;

    public ConsoleView(GameModel gameModel) {
        this.gameModel = gameModel;
        this.keyboard = new Scanner(System.in);
        this.picker = new CommandLineUserActionPicker();
    }

    public void play() {

        System.out.print("Enter dimension: ");
        int dimension = keyboard.nextInt();
        gameModel.startGame(dimension, GameDifficulty.EASY);

        CommandLineUserAction userAction;
        while (true) {
            printBoard();

            userAction = picker.getUserAction(new String[]{"open", "mark"});

            switch (userAction.getMode()) {
                case "open":
                    gameModel.openCell(userAction.getRow(), userAction.getCol());
                    break;
                case "mark":
                    gameModel.changeMarkedAsBomb(userAction.getRow(), userAction.getCol());
                    break;
            }

            switch (gameModel.getState()) {
                case WON:
                    System.out.println("You've won!");
                    System.exit(0);
                    break;
                case LOST:
                    System.out.println("You've lost!");
                    System.exit(0);
                    break;
            }

        }
    }

    private void printBoard() {
        printHeader();
        for (int row = 0; row < gameModel.getRowCount(); row++) {
            System.out.print(row + " |");
            for (int col = 0; col < gameModel.getColCount(); col++) {
                CellInfo cellInfo = gameModel.getCellInfo(row, col);
                System.out.print(getIcon(cellInfo));
            }
            System.out.println();
        }

    }

    private String getIcon(CellInfo cellInfo) {
        if (cellInfo.isMarkedAsBomb()) {
            return "@ ";
        }
        if (!cellInfo.isVisited()) {
            return "· ";
        }
        return cellInfo.getCountOfNeighbourMines() + " ";
    }

    private void printHeader() {
        String headerCols = "  |";
        String headerLine = "--|";
        for (int i = 0; i < gameModel.getColCount(); i++) {
            headerCols = headerCols.concat(i + " ");
            headerLine = headerLine.concat("--");
        }
        System.out.println(headerCols);
        System.out.println(headerLine);
    }
}
