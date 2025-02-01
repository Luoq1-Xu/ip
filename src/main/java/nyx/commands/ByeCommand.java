package nyx.commands;

import nyx.Storage;
import nyx.TaskList;
import nyx.Ui;
import nyx.exceptions.NyxException;


/**
 * Represents a command to exit the application.
 */
public class ByeCommand extends Command {

    /**
     * Executes the ByeCommand, displaying a goodbye message.
     *
     * @param taskList The task list.
     * @param storage  The storage handler.
     * @param ui       The user interface handler.
     * @throws NyxException If an error occurs during execution.
     */
    public void execute(TaskList taskList, Storage storage, Ui ui) throws NyxException {
        ui.displayString("Goodbye!");
    }
}
