package nyx.commands;

import java.time.LocalDate;

import nyx.exceptions.InvalidUsageException;
import nyx.exceptions.NyxException;
import nyx.Storage;
import nyx.TaskList;
import nyx.Ui;
import nyx.tasks.DeadlineTask;

public class DeadlineCommand extends Command{

    private final String command;

    public DeadlineCommand(String input) {
        this.command = input;
    }

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

    private DeadlineTask getDeadlineTask() {
        String parts = this.command.substring(9);
        String[] splitParts = parts.split(" -by ");
        String taskName = splitParts[0].trim();
        String deadlineString = splitParts[1];

        // Parse the deadline String
        LocalDate deadline = LocalDate.parse(deadlineString);

        DeadlineTask newTask = new DeadlineTask(taskName, deadline);

        return new DeadlineTask(taskName, deadline);
    }
}
