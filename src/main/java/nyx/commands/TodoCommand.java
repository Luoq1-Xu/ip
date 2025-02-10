package nyx.commands;

import nyx.Storage;
import nyx.TaskList;
import nyx.Ui;
import nyx.exceptions.InvalidUsageException;
import nyx.exceptions.NyxException;
import nyx.tasks.TodoTask;

/**
 * Represents a command to add a todo task.
 */
public class TodoCommand extends Command {

    private final String command;

    /**
     * Constructs a new TodoCommand instance with the specified input command.
     *
     * @param input The input command string.
     */
    public TodoCommand(String input) {
        super();
        this.command = input;
    }

    /**
     * Executes the TodoCommand, adding a todo task to the task list.
     *
     * @param taskList The task list.
     * @param storage  The storage handler.
     * @param ui       The user interface handler.
     * @return     The output string to be displayed.
     * @throws NyxException If an error occurs during execution.
     */
    public String execute(TaskList taskList, Storage storage, Ui ui) throws NyxException {
        try {
            String args = this.command.substring(5);
            TodoTask newTask = new TodoTask(args);

            String output = taskList.addTask(newTask);
            storage.saveTaskData(taskList.toSaveFormat());
            return output;
        } catch (Exception e) {
            throw new InvalidUsageException("Wrong Usage. Correct usage: todo [task name] ");
        }
    }
}
