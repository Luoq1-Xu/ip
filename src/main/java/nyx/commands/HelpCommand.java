package nyx.commands;

import nyx.Storage;
import nyx.TaskList;
import nyx.Ui;
import nyx.exceptions.NyxException;

/**
 * Represents a help command that displays useful information.
 */
public class HelpCommand extends Command {

    private static final String helpMessage = """
        List of commands:

        Create a todo task: todo [task name]
        Create a deadline task: deadline [task name] -by [deadline]
        Create a event task: event [event name] -start [time] -end [time]
        List all tasks: list
        Mark a task as complete: mark [task id]
        Mark a task as incomplete: unmark [task id]
        Delete a task: delete [task id]
        Find a task: find [query]
        Tag a task: Tag [task id] [tag name]
        Show help menu: help
        Exit the application: bye
        """;
    /**
     * Executes the HelpCommand, displaying a message of all commands.
     *
     * @param taskList The task list.
     * @param storage  The storage handler.
     * @param ui       The user interface handler.
     * @return     The output string to be displayed.
     * @throws NyxException Always thrown to indicate an unrecognized command.
     */
    public String execute(TaskList taskList, Storage storage, Ui ui) throws NyxException {
        return HelpCommand.helpMessage;
    }
}
