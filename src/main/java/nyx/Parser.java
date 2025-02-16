package nyx;

import nyx.commands.ByeCommand;
import nyx.commands.Command;
import nyx.commands.DeadlineCommand;
import nyx.commands.DeleteCommand;
import nyx.commands.EventCommand;
import nyx.commands.FindCommand;
import nyx.commands.HelpCommand;
import nyx.commands.ListCommand;
import nyx.commands.MarkCommand;
import nyx.commands.TagCommand;
import nyx.commands.TodoCommand;
import nyx.commands.UnknownCommand;
import nyx.commands.UnmarkCommand;


/**
 * The Parser class is responsible for parsing user input and returning the appropriate command.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding command.
     *
     * @param input The user input string.
     * @return The command corresponding to the user input.
     */
    @SuppressWarnings("checkstyle:Indentation") // Ignore lambda-style case lines
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
            case "tag" -> new TagCommand(input);
            case "help" -> new HelpCommand();
            default -> new UnknownCommand();
        };
    }
}
