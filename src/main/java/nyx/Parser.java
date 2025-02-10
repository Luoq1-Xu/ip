package nyx;

import nyx.commands.ByeCommand;
import nyx.commands.Command;
import nyx.commands.DeadlineCommand;
import nyx.commands.DeleteCommand;
import nyx.commands.EventCommand;
import nyx.commands.FindCommand;
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
        // Get command as first word of the input
        String trimmedInput = input.trim();
        String[] parts = trimmedInput.split(" ", 2);
        String command = parts[0];

        return switch (command) {
            case "todo" -> new TodoCommand(input);
            case "deadline" -> new DeadlineCommand(input);
            case "event" -> new EventCommand(input);
            case "list" -> new ListCommand();
            case "bye" -> new ByeCommand();
            case "mark" -> new MarkCommand(input);
            case "unmark" -> new UnmarkCommand(input);
            case "delete" -> new DeleteCommand(input);
            case "find" -> new FindCommand(input);
            default -> new UnknownCommand();
        };
    }
}
