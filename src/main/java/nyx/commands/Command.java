package nyx.commands;
import nyx.Storage;
import nyx.TaskList;
import nyx.Ui;
import nyx.exceptions.NyxException;


/**
 * Represents an abstract command.
 * All specific command types should extend this class.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param taskList The task list.
     * @param storage  The storage handler.
     * @param ui       The user interface handler.
     * @return         The output after executing the command.
     * @throws NyxException If an error occurs during execution.
     *
     */
    public abstract String execute(TaskList taskList, Storage storage, Ui ui) throws NyxException;
}
