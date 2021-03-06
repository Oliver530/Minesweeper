package view;

import view.console.useraction.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by olivergerhardt on 31.08.17.
 */
public class UserActionFactoryTest {

    @Test
    public void getUserActionExit() {
        String input = "exit";
        UserAction userAction = UserActionFactory.getUserAction(input, 0, 0);

        assertTrue(userAction instanceof UserActionExit);
    }

    @Test
    public void getUserActionOpenWithTooManyParameters() {
        String input = "open 1 2 3";
        UserAction userAction = UserActionFactory.getUserAction(input, 3,3);

        assertTrue(userAction instanceof UserActionInvalid);
    }

    @Test
    public void getUserActionOpenOutOfBoundsNegative() {
        String input = "open 1 -1";
        UserAction userAction = UserActionFactory.getUserAction(input, 3,3);

        assertTrue(userAction instanceof UserActionInvalid);
    }

    @Test
    public void getUserActionOpenOutOfBoundsPositive() {
        String input = "open 1 3";
        UserAction userAction = UserActionFactory.getUserAction(input, 3,3);

        assertTrue(userAction instanceof UserActionInvalid);
    }

    @Test
    public void getUserActionOpenWithTooFewParameters() {
        String input = "open 1";
        UserAction userAction = UserActionFactory.getUserAction(input, 3, 3);

        assertTrue(userAction instanceof UserActionInvalid);
    }

    @Test
    public void getUserActionOpen() {
        String input = "open 1 2";
        UserAction userAction = UserActionFactory.getUserAction(input, 3, 3);

        assertTrue(userAction instanceof UserActionOpen);
        UserActionOpen userActionOpen = (UserActionOpen) userAction;
        assertEquals(1, userActionOpen.getRow());
        assertEquals(2, userActionOpen.getCol());
    }

    @Test
    public void getUserActionMark() {
        String input = "mark 1 2";
        UserAction userAction = UserActionFactory.getUserAction(input, 3, 3);

        assertTrue(userAction instanceof UserActionMark);
        UserActionMark userActionMark = (UserActionMark) userAction;
        assertEquals(1, userActionMark.getRow());
        assertEquals(2, userActionMark.getCol());
    }

}
