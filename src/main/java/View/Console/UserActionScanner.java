package View.Console;

import View.Console.UserAction.UserAction;
import View.Console.UserAction.UserActionExit;
import View.Console.UserAction.UserActionMark;
import View.Console.UserAction.UserActionOpen;

/**
 * Created by olivergerhardt on 31.08.17.
 */
public class UserActionScanner {

    public static UserAction getUserAction(String input) {

        if (input.equals("exit")) {
            return new UserActionExit();
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

        if (inputArray[0].equals("open")) {
            return new UserActionOpen(row, col);
        }
        if (inputArray[0].equals("mark")) {
            return new UserActionMark(row, col);
        }
        return null;
    }
}
