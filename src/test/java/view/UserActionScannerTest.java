package View;

import View.Console.UserAction.UserAction;
import View.Console.UserAction.UserActionExit;
import View.Console.UserAction.UserActionMark;
import View.Console.UserAction.UserActionOpen;
import View.Console.UserActionScanner;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by olivergerhardt on 31.08.17.
 */
public class UserActionScannerTest {

    @Test
    public void getUserActionExit() {
        String input = "exit";
        UserAction userAction = UserActionScanner.getUserAction(input);

        assertTrue(userAction instanceof UserActionExit);
    }

    @Test
    public void getUserActionOpenWithTooManyParameters() {
        String input = "open 1 2 3";
        UserAction userAction = UserActionScanner.getUserAction(input);

        assertNull(userAction);
    }

    @Test
    public void getUserActionOpenWithTooFewParameters() {
        String input = "open 1";
        UserAction userAction = UserActionScanner.getUserAction(input);

        assertNull(userAction);
    }

    @Test
    public void getUserActionOpen() {
        String input = "open 1 2";
        UserAction userAction = UserActionScanner.getUserAction(input);

        assertTrue(userAction instanceof UserActionOpen);
        UserActionOpen userActionOpen = (UserActionOpen) userAction;
        assertEquals(1, userActionOpen.getRow());
        assertEquals(2, userActionOpen.getCol());
    }

    @Test
    public void getUserActionMark() {
        String input = "mark 1 2";
        UserAction userAction = UserActionScanner.getUserAction(input);

        assertTrue(userAction instanceof UserActionMark);
        UserActionMark userActionMark = (UserActionMark) userAction;
        assertEquals(1, userActionMark.getRow());
        assertEquals(2, userActionMark.getCol());
    }

}
