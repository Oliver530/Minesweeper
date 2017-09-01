package view.console;

import minesweeper4java.MinesweeperGameModel;
import model.Board;
import model.state.GameStateLost;
import model.state.GameStateWon;
import view.console.useraction.UserAction;
import minesweeper4java.MinesweeperView;
import model.GameDifficulty;

import java.util.Scanner;


/**
 * Created by olivergerhardt on 30.08.17.
 */
public class ConsoleView implements MinesweeperView {

    private final MinesweeperGameModel gameModel;
    private final ConsoleViewDrawer drawer;
    private final Scanner keyboard;


    public ConsoleView(MinesweeperGameModel gameModel) {
        this.gameModel = gameModel;
        drawer = new ConsoleViewDrawer(gameModel);
        this.keyboard = new Scanner(System.in);
    }


    public void play() {

        int dimension = getPositiveIntegerFromUser(keyboard, "Enter dimension (>3): ");
        gameModel.startGame(new Board(dimension, GameDifficulty.EASY));
        System.out.println("There are " + gameModel.getCountOfMines() + " mines. Good luck!");
        drawer.draw();

        UserAction userAction;
        while (true) {
            userAction = getUserAction();
            userAction.perform(gameModel, drawer);

            if (gameModel.getState() instanceof GameStateWon) {
                System.out.println("You've won!");
                break;
            } else if (gameModel.getState() instanceof GameStateLost) {
                System.out.println("You've lost!");
                break;
            }
        }

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
        UserAction userAction = null;
        while (userAction == null) {
            System.out.print("Command: ");
            String userInput = keyboard.nextLine();
            userAction = UserActionScanner.getUserAction(userInput);
        }
        return userAction;
    }
}
