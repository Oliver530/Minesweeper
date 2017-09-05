package view.console;

import minesweeper4java.GameModel;
import model.Board;
import view.console.useraction.UserAction;
import minesweeper4java.View;
import util.GameDifficulty;
import view.console.useraction.UserActionFactory;

import java.util.Arrays;
import java.util.Scanner;


/**
 * Created by olivergerhardt on 30.08.17.
 */
public final class ConsoleView implements View {

    private final GameModel gameModel;
    private final ConsoleViewDrawer drawer;
    private final Scanner keyboard;

    private int rows;
    private int columns;


    public ConsoleView(final GameModel gameModel) {
        this.gameModel = gameModel;
        drawer = new ConsoleViewDrawer(gameModel);
        keyboard = new Scanner(System.in);
    }


    @Override
    public void play() {
        do {
            setup();
            performUserActions();
        } while (playAgain());
        System.out.println("Goodbye!");
    }

    private void setup() {
        this.rows = getIntFromUser("Enter count of rows (>" + (Board.ROWS_MINIMUM - 1) + "): ", Board.ROWS_MINIMUM);
        this.columns = getIntFromUser("Enter count of columns (>" + (Board.COLUMNS_MINIMUM - 1) + "): ", Board.COLUMNS_MINIMUM);
        gameModel.setBoard(new Board(rows, columns, GameDifficulty.EASY));
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
                System.out.println();
                break;
            } else if (gameModel.gameLost()) {
                gameOver();
                System.out.println("*******************");
                System.out.println("Sorry. You've lost!");
                System.out.println("*******************");
                System.out.println();
                break;
            }
        }
    }

    private void gameOver() {
        System.out.println();
        gameModel.visitAllAndRemoveMarks();
        drawer.draw();
    }

    private int getIntFromUser(final String prompt, final int min) {
        int value = min - 1;
        while (value < min) {
            System.out.print(prompt);
            String input = keyboard.nextLine();
            try {
                value = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                continue;
            }
        }
        return value;
    }

    private String getStringFromUser(final String prompt, final String[] validInputs) {
        String input = "";
        while (true) {
            System.out.print(prompt);
            input = keyboard.nextLine();

            if (Arrays.asList(validInputs).contains(input)) {
                break;
            }
        }
        return input;
    }

    private UserAction getUserAction() {
        System.out.print("Command: ");
        String userInput = keyboard.nextLine();
        return UserActionFactory.getUserAction(userInput, rows, columns);
    }

    private boolean playAgain() {
        String playAgain = getStringFromUser("Play again (y/n)? ", new String[]{"y", "n"});
        if ("y".equals(playAgain)) {
            return true;
        }
        return false;
    }

}
