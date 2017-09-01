package view.console;

import view.console.useraction.*;

/**
 * Created by olivergerhardt on 31.08.17.
 */
public class UserActionScanner {

    public static UserAction getUserAction(String input) {

        if (input.equals("exit")) {
            return new UserActionExit();
        }
        if (input.equals("help")) {
            return new UserActionHelp();
        }

        String[] inputArray = input.split("\\s+");
        if (inputArray.length != 3) {
            return null;
        }

        int row;
        int col;
        try {
            row = Integer.parseInt(inputArray[1]);
            col = Integer.parseInt(inputArray[2]);
        } catch (NumberFormatException e) {
            return null;
        }

        if (inputArray[0].equals("open") || inputArray[0].equals("o")) {
            return new UserActionOpen(row, col);
        }
        if (inputArray[0].equals("mark") || inputArray[0].equals("m")) {
            return new UserActionMark(row, col);
        }
        return null;
    }
}
