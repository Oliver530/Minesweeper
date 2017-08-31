package View;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by olivergerhardt on 26.08.17.
 */
public class CommandLineUserActionPicker {

    private Scanner keyboard;

    public CommandLineUserActionPicker() {
        keyboard = new Scanner(System.in);
    }

    public CommandLineUserAction getUserAction(String[] validCommands, String exitCommand) {
        int row;
        int col;
        String mode;

        do {
            System.out.print("Command: ");
            String userInputs = keyboard.nextLine();

            String[] userInput = userInputs.split("\\s+");
            if (userInput[0].equals(exitCommand)) {
                return new CommandLineUserAction(exitCommand, 0, 0);
            }


            if (userInput.length != 3) {
                continue;
            }
            if (Arrays.asList(validCommands).contains(userInput[0])) {
                mode = userInput[0];
            } else {
                continue;
            }
            try {
                row = Integer.parseInt(userInput[1]);
            } catch (NumberFormatException e) {
                continue;
            }
            try {
                col = Integer.parseInt(userInput[2]);
            } catch (NumberFormatException e) {
                continue;
            }
            return new CommandLineUserAction(mode, row, col);
        } while (true);


    }

}
