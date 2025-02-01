package nyx.commands;

import java.time.LocalDate;

import nyx.Storage;
import nyx.TaskList;
import nyx.Ui;
import nyx.exceptions.InvalidUsageException;
import nyx.exceptions.NyxException;
import nyx.tasks.DeadlineTask;

/**
 * Represents a command to add a deadline task.
 */
public class DeadlineCommand extends Command {

    private final String command;

    /**
     * Constructs a new DeadlineCommand instance with the specified input command.
     *
     * @param input The input command string.
     */
    public DeadlineCommand(String input) {
        this.command = input;
    }

    /**
     * Executes the DeadlineCommand, adding a deadline task to the task list.
     *
     * @param taskList The task list.
     * @param storage  The storage handler.
     * @param ui       The user interface handler.
     * @throws NyxException If an error occurs during execution.
     */
    public void execute(TaskList taskList, Storage storage, Ui ui) throws NyxException {
        try {
            DeadlineTask newTask = getDeadlineTask();
            taskList.addTask(newTask, ui);
            storage.saveTaskData(taskList.toSaveFormat());
        } catch (Exception e) {
            throw new InvalidUsageException("Wrong Usage. Correct usage: deadline [task name] -by [deadline]\n"
                    + "Dates should be in YYYY-MM-DD format. For example: 2025-02-19");
        }
    }

    /**
     * Parses the input command to create a DeadlineTask.
     *
     * @return The created DeadlineTask.
     */
    private DeadlineTask getDeadlineTask() {
        String parts = this.command.substring(9);
        String[] splitParts = parts.split(" -by ");
        String taskName = splitParts[0].trim();
        String deadlineString = splitParts[1];

        // Parse the deadline string
        LocalDate deadline = LocalDate.parse(deadlineString);

        return new DeadlineTask(taskName, deadline);
    }
}
