package nyx.commands;

import nyx.exceptions.NyxException;
import nyx.Storage;
import nyx.TaskList;
import nyx.Ui;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the ListCommand, displaying the current list of tasks.
     *
     * @param taskList The task list.
     * @param storage  The storage handler.
     * @param ui       The user interface handler.
     * @throws NyxException If an error occurs during execution.
     */
    public void execute(TaskList taskList, Storage storage, Ui ui) throws NyxException {
        ui.displayString(taskList.getTaskList());
    }
}