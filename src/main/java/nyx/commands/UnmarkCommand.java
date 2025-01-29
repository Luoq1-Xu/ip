package nyx.commands;

import nyx.exceptions.InvalidUsageException;
import nyx.exceptions.NyxException;
import nyx.Storage;
import nyx.TaskList;
import nyx.Ui;

/**
 * Represents a command to unmark a task as incomplete.
 */
public class UnmarkCommand extends Command {
    private final String command;

    /**
     * Constructs a new UnmarkCommand instance with the specified input command.
     *
     * @param command The input command string.
     */
    public UnmarkCommand(String command) {
        super();
        this.command = command;
    }

    /**
     * Executes the UnmarkCommand, marking a task as incomplete in the task list.
     *
     * @param taskList The task list.
     * @param storage  The storage handler.
     * @param ui       The user interface handler.
     * @throws NyxException If an error occurs during execution.
     */
    public void execute(TaskList taskList, Storage storage, Ui ui) throws NyxException {
        try {
            String[] splitInput = this.command.split(" ");
            int taskIndex = Integer.parseInt(splitInput[1]) - 1;
            taskList.markTaskAsIncomplete(taskIndex, ui);
            storage.saveTaskData(taskList.toSaveFormat());
        } catch (Exception e) {
            throw new InvalidUsageException("Wrong usage. Correct usage: unmark [task id]");
        }
    }
}