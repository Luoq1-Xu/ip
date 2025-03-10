package nyx.commands;

import nyx.Storage;
import nyx.TaskList;
import nyx.Ui;
import nyx.exceptions.InvalidUsageException;
import nyx.exceptions.NyxException;


/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {
    private final String command;

    /**
     * Constructs a new DeleteCommand instance with the specified input command.
     *
     * @param command The input command string.
     */
    public DeleteCommand(String command) {
        super();
        this.command = command;
    }

    /**
     * Executes the DeleteCommand, deleting a task from the task list.
     *
     * @param taskList The task list.
     * @param storage  The storage handler.
     * @param ui       The user interface handler.
     * @return     The output string to be displayed.
     * @throws NyxException If an error occurs during execution.
     */
    public String execute(TaskList taskList, Storage storage, Ui ui) throws NyxException {
        try {
            // Get index of task
            String[] splitInput = this.command.split(" ");
            int taskIndex = Integer.parseInt(splitInput[1]) - 1;

            // Delete the task and update the storage
            String output = taskList.deleteTask(taskIndex);
            storage.saveTaskData(taskList.toSaveFormat());
            return output;
        } catch (Exception e) {
            throw new InvalidUsageException("Wrong usage. Correct usage: delete [task id]");
        }
    }
}
