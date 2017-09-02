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
public class ConsoleView implements View {

    private final GameModel gameModel;
    private final ConsoleViewDrawer drawer;
    private final Scanner keyboard;

    private int countOfRows;
    private int countOfColumns;


    public ConsoleView(GameModel gameModel) {
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
        this.countOfRows = getPositiveIntegerFromUser(keyboard, "Enter count of rows (>" + (Board.MINIMUM_ROWS - 1) + "): ");
        this.countOfColumns = getPositiveIntegerFromUser(keyboard, "Enter count of columns (>" + (Board.MINIMUM_COLUMNS - 1) + "): ");
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
                System.out.println("You've won!");
                return;
            } else if (gameModel.gameLost()) {
                System.out.println("You've lost!");
                return;
            }
        }
    }

    private void gameOver() {
        System.out.println();
        gameModel.visitAllAndRemoveMarks();
        drawer.draw();
        System.exit(0);
    }

    private int getPositiveIntegerFromUser(Scanner scanner, String prompt) {
        int value = -1;
        while (value < 4) {
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

    public int getCountOfRows() {
        return countOfRows;
    }

    public int getCountOfColumns() {
        return countOfColumns;
    }
}
