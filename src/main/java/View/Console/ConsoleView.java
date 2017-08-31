package View.Console;

import View.Console.UserAction.UserAction;
import minesweeper4java.MinesweeperView;
import model.GameDifficulty;
import model.GameModel;

import java.util.Scanner;


/**
 * Created by olivergerhardt on 30.08.17.
 */
public class ConsoleView implements MinesweeperView {

    private final GameModel gameModel;
    private final ConsoleViewDrawer drawer;
    private final Scanner keyboard;


    public ConsoleView(GameModel gameModel) {
        this.gameModel = gameModel;
        drawer = new ConsoleViewDrawer(gameModel);
        this.keyboard = new Scanner(System.in);
    }


    public void play() {

        int dimension = getPositiveIntegerFromUser(keyboard, "Enter column count (positive integer): ");
        gameModel.startGame(dimension, GameDifficulty.EASY);
        System.out.println("There are " + gameModel.getCountOfMines() + " mines. Good luck!");

        UserAction userAction;
        commandScanner: while (true) {
            drawer.draw();

            userAction = getUserAction();
            userAction.perform(gameModel);
            System.out.println();

            switch (gameModel.getState()) {
                case WON:
                    System.out.println("You've won!");
                    break commandScanner;
                case LOST:
                    System.out.println("You've lost!");
                    break commandScanner;
            }
        }

        System.out.println();
        gameModel.visitAllAndRemoveMarks();
        drawer.draw();
        System.exit(0);
    }

    private int getPositiveIntegerFromUser(Scanner scanner, String prompt) {
        int value = -1;
        while (value < 1) {
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
        UserAction userAction = null;
        while (userAction == null) {
            System.out.print("Command: ");
            String userInput = keyboard.nextLine();
            userAction = UserActionScanner.getUserAction(userInput);
        }
        return userAction;
    }
}