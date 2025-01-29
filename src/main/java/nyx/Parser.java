package nyx;

import nyx.commands.ByeCommand;
import nyx.commands.Command;
import nyx.commands.DeadlineCommand;
import nyx.commands.DeleteCommand;
import nyx.commands.EventCommand;
import nyx.commands.ListCommand;
import nyx.commands.MarkCommand;
import nyx.commands.TodoCommand;
import nyx.commands.UnknownCommand;
import nyx.commands.UnmarkCommand;

/**
 * The Parser class is responsible for parsing user input and returning the appropriate command.
 */
public class Parser {

    /**
     * Enum representing the different types of commands.
     */
    private enum CommandType {
        TODO,
        DEADLINE,
        EVENT,
        MARK,
        UNMARK,
        DELETE,
        LIST,
        BYE,
        FILTER,
        UNKNOWN
    }

    /**
     * Parses the user input and returns the corresponding command.
     *
     * @param input The user input string.
     * @return The command corresponding to the user input.
     */
    public static Command parse(String input) {
        if (input.startsWith("todo ")) {
            return new TodoCommand(input);
        } else if (input.startsWith("deadline ")) {
            return new DeadlineCommand(input);
        } else if (input.startsWith("event ")) {
            return new EventCommand(input);
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.equals("bye")) {
            return new ByeCommand();
        } else if (input.startsWith("mark ")) {
            return new MarkCommand(input);
        } else if (input.startsWith("unmark ")) {
            return new UnmarkCommand(input);
        } else if (input.startsWith("delete ")) {
            return new DeleteCommand(input);
        } else {
            return new UnknownCommand();
        }
    }
}