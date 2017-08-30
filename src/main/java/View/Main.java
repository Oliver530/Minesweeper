package View;

import minesweeper4java.GameModel;

/**
 * Created by olivergerhardt on 30.08.17.
 */
public class Main {

    public static void main(String[] args) {
        GameModel model = new GameModel();
        ConsoleView view = new ConsoleView(model);
        view.play();
    }
}
