package view.console.useraction;

/**
 * Created by olivergerhardt on 31.08.17.
 *
 * Convert an input string to an UserAction
 */
public class UserActionFactory {

    public static UserAction getUserAction(final String input, final int rowCount, final int colCount) {

        if (input.equals("exit")) {
            return new UserActionExit();
        }
        if (input.equals("help")) {
            return new UserActionHelp();
        }

        return getActionWithRowAndCol(input, rowCount, colCount);
    }

    private static UserAction getActionWithRowAndCol(final String input, final int rowCount, final int colCount) {
        String[] inputArray = input.split("\\s+");
        if (inputArray.length != 3) {
            return new UserActionInvalid();
        }

        int row;
        int col;
        try {
            row = Integer.parseInt(inputArray[1]);
            col = Integer.parseInt(inputArray[2]);
        } catch (NumberFormatException e) {
            return new UserActionInvalid();
        }

        if (row < 0 || row >= rowCount) {
            return new UserActionInvalid();
        }

        if (col < 0 || col >= colCount) {
            return new UserActionInvalid();
        }

        if (inputArray[0].equals("open") || inputArray[0].equals("o")) {
            return new UserActionOpen(row, col);
        }
        if (inputArray[0].equals("mark") || inputArray[0].equals("m")) {
            return new UserActionMark(row, col);
        }
        return new UserActionInvalid();
    }
}
