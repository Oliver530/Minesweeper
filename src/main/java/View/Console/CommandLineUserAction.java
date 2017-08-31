package View;

/**
 * Created by olivergerhardt on 26.08.17.
 */
public class CommandLineUserAction {

    private final String mode;
    private final int row;
    private final int col;

    public CommandLineUserAction(String mode, int row, int col) {
        this.mode = mode;
        this.row = row;
        this.col = col;
    }

    public String getMode() {
        return mode;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
