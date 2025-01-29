package nyx.commands;

import nyx.exceptions.InvalidUsageException;
import nyx.exceptions.NyxException;
import nyx.Storage;
import nyx.TaskList;
import nyx.Ui;
import nyx.tasks.TodoTask;

public class TodoCommand extends Command {

    private final String command;

    public TodoCommand(String input) {
        this.command = input;
    }

    public void execute(TaskList taskList, Storage storage, Ui ui) throws NyxException {
        try {
            String args = this.command.substring(5);
            TodoTask newTask = new TodoTask(args);
            taskList.addTask(newTask, ui);
            storage.saveTaskData(taskList.toSaveFormat());
        } catch (Exception e) {
            throw new InvalidUsageException("Wrong Usage. Correct usage: todo [task name] ");
        }
    }
}
