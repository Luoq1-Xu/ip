package nyx.commands;

import nyx.Storage;
import nyx.TaskList;
import nyx.Ui;
import nyx.exceptions.InvalidUsageException;
import nyx.exceptions.NyxException;

/**
 * Represents a command to mark a task as complete.
 */
public class MarkCommand extends Command {
    private final String command;

    /**
     * Constructs a new MarkCommand instance with the specified input command.
     *
     * @param command The input command string.
     */
    public MarkCommand(String command) {
        super();
        this.command = command;
    }

    /**
     * Executes the MarkCommand, marking a task as complete in the task list.
     *
     * @param taskList The task list.
     * @param storage  The storage handler.
     * @param ui       The user interface handler.
     * @return     The output string to be displayed.
     * @throws NyxException If an error occurs during execution.
     */
    public String execute(TaskList taskList, Storage storage, Ui ui) throws NyxException {
        try {
            String[] splitInput = this.command.split(" ");
            int taskIndex = Integer.parseInt(splitInput[1]) - 1;
            String output = taskList.markTaskAsComplete(taskIndex);
            storage.saveTaskData(taskList.toSaveFormat());
            return output;
        } catch (Exception e) {
            throw new InvalidUsageException("Wrong usage. Correct usage: mark [task id]");
        }
    }
}
