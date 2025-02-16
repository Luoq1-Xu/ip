package nyx.commands;

import nyx.Storage;
import nyx.TaskList;
import nyx.Ui;
import nyx.exceptions.NyxException;
import nyx.exceptions.UnknownCommandException;

/**
 * Represents a command that is not recognized.
 */
public class UnknownCommand extends Command {

    /**
     * Executes the UnknownCommand, throwing an UnknownCommandException.
     *
     * @param taskList The task list.
     * @param storage  The storage handler.
     * @param ui       The user interface handler.
     * @return     The output string to be displayed.
     * @throws NyxException Always thrown to indicate an unrecognized command.
     */
    public String execute(TaskList taskList, Storage storage, Ui ui) throws NyxException {
        throw new UnknownCommandException("Unrecognized command.\nUse `help` to see the list of commands.");
    }
}
