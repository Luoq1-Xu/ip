package nyx.commands;

import nyx.exceptions.NyxException;
import nyx.Storage;
import nyx.TaskList;
import nyx.Ui;

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
     * @throws NyxException If an error occurs during execution.
     */
    public abstract void execute(TaskList taskList, Storage storage, Ui ui) throws NyxException;
}