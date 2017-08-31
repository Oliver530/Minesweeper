package View.Console;

import minesweeper4java.MinesweeperView;
import model.GameDifficulty;
import model.GameModel;

import java.util.Scanner;


/**
 * Created by olivergerhardt on 30.08.17.
 */
public class ConsoleView implements MinesweeperView {

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


}
