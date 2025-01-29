// filepath: src/test/java/nyx/ParserTest.java

package nyx;

import nyx.commands.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void testParseTodoCommand() {
        Command command = Parser.parse("todo read book");
        assertInstanceOf(TodoCommand.class, command);
    }

    @Test
    public void testParseDeadlineCommand() {
        Command command = Parser.parse("deadline return book -by 2023-10-25");
        assertInstanceOf(DeadlineCommand.class, command);
    }

    @Test
    public void testParseEventCommand() {
        Command command = Parser.parse("event project meeting -start 2023-10-25 -end 2023-10-26");
        assertInstanceOf(EventCommand.class, command);
    }

    @Test
    public void testParseListCommand() {
        Command command = Parser.parse("list");
        assertInstanceOf(ListCommand.class, command);
    }

    @Test
    public void testParseByeCommand() {
        Command command = Parser.parse("bye");
        assertInstanceOf(ByeCommand.class, command);
    }

    @Test
    public void testParseMarkCommand() {
        Command command = Parser.parse("mark 1");
        assertInstanceOf(MarkCommand.class, command);
    }

    @Test
    public void testParseUnmarkCommand() {
        Command command = Parser.parse("unmark 1");
        assertInstanceOf(UnmarkCommand.class, command);
    }

    @Test
    public void testParseDeleteCommand() {
        Command command = Parser.parse("delete 1");
        assertInstanceOf(DeleteCommand.class, command);
    }

    @Test
    public void testParseUnknownCommand() {
        Command command = Parser.parse("unknown command");
        assertInstanceOf(UnknownCommand.class, command);
    }
}