package view.console;

import minesweeper4java.GameModel;
import model.Board;
import view.console.useraction.UserAction;
import minesweeper4java.View;
import util.GameDifficulty;
import view.console.useraction.UserActionFactory;

import java.util.Scanner;


/**
 * Created by olivergerhardt on 30.08.17.
 */
public final class ConsoleView implements View {

    private final GameModel gameModel;
    private final ConsoleViewDrawer drawer;
    private final Scanner keyboard;

    private int countOfRows;
    private int countOfColumns;


    public ConsoleView(final GameModel gameModel) {
        this.gameModel = gameModel;
        drawer = new ConsoleViewDrawer(gameModel);
        keyboard = new Scanner(System.in);
    }


    public void play() {
        setup();
        performUserActions();
        gameOver();
    }

    private void setup() {
        this.countOfRows = getPositiveIntegerFromUser(keyboard, "Enter count of rows (>" + (Board.MINIMUM_ROWS - 1) + "): ", Board.MINIMUM_ROWS);
        this.countOfColumns = getPositiveIntegerFromUser(keyboard, "Enter count of columns (>" + (Board.MINIMUM_COLUMNS - 1) + "): ", Board.MINIMUM_COLUMNS);
        gameModel.setBoard(new Board(countOfRows, countOfColumns, GameDifficulty.EASY));
        System.out.println("There are " + gameModel.getCountOfMines() + " mines. Good luck!");
        drawer.draw();
    }

    private void performUserActions() {
        UserAction userAction;
        while (true) {
            userAction = getUserAction();
            userAction.perform(gameModel, drawer);

            if (gameModel.gameWon()) {
                gameOver();
                System.out.println("***************************");
                System.out.println("Congratulation. You've won!");
                System.out.println("***************************");
                break;
            } else if (gameModel.gameLost()) {
                gameOver();
                System.out.println("*******************");
                System.out.println("Sorry. You've lost!");
                System.out.println("*******************");
                break;
            }
        }
        System.exit(0);
    }

    private void gameOver() {
        System.out.println();
        gameModel.visitAllAndRemoveMarks();
        drawer.draw();

    }

    private int getPositiveIntegerFromUser(final Scanner scanner, final String prompt, final int minimum) {
        int value = -1;
        while (value < minimum) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                value = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                continue;
            }
        }
        return value;
    }

    private UserAction getUserAction() {
        System.out.print("Command: ");
        String userInput = keyboard.nextLine();
        return UserActionFactory.getUserAction(userInput, getCountOfRows(), getCountOfColumns());
    }

    private int getCountOfRows() {
        return countOfRows;
    }

    private int getCountOfColumns() {
        return countOfColumns;
    }
}
