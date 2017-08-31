package View;

import minesweeper4java.CellInfo;
import minesweeper4java.GameDifficulty;
import minesweeper4java.GameModel;

import java.util.Scanner;


/**
 * Created by olivergerhardt on 30.08.17.
 */
public class ConsoleView {

    private final static String ICON_NOT_VISITED = " · ";
    private final static String ICON_IS_MARKED_AS_BOMB = " @ ";
    private final static String ICON_IS_BOMB = " X ";


    private final GameModel gameModel;
    private final Scanner keyboard;
    private final CommandLineUserActionPicker picker;


    public ConsoleView(GameModel gameModel) {
        this.gameModel = gameModel;
        this.keyboard = new Scanner(System.in);
        this.picker = new CommandLineUserActionPicker();
    }

    public int getDimension(Scanner scanner, String prompt) {
        int dimension = -1;
        while (dimension < 1) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                dimension = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                continue;
            }
        }
        return dimension;
    }

    public void play() {

        int dimension = getDimension(keyboard, "Enter column count (positive integer): ");
        gameModel.startGame(dimension, GameDifficulty.EASY);

        CommandLineUserAction userAction;
        while (true) {
            printBoard();

            userAction = picker.getUserAction(new String[]{"open", "mark"}, "exit");

            switch (userAction.getMode()) {
                case "open":
                    gameModel.openCell(userAction.getRow(), userAction.getCol());
                    break;
                case "mark":
                    gameModel.changeMarkedAsBomb(userAction.getRow(), userAction.getCol());
                    break;
                case "exit":
                    gameModel.changeMarkedAsBomb(userAction.getRow(), userAction.getCol());
                    System.exit(0);
            }

            switch (gameModel.getState()) {
                case WON:
                    System.out.println("You've won!");
                    gameModel.visitAllAndRemoveMarks();
                    printBoard();
                    System.exit(0);
                case LOST:
                    System.out.println("You've lost!");
                    gameModel.visitAllAndRemoveMarks();
                    printBoard();
                    System.exit(0);
            }

        }
    }

    private void printBoard() {
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
