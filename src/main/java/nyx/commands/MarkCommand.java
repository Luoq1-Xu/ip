package nyx.commands;

import nyx.exceptions.InvalidUsageException;
import nyx.exceptions.NyxException;
import nyx.Storage;
import nyx.TaskList;
import nyx.Ui;

public class MarkCommand extends Command {
    private final String command;

    public MarkCommand(String command) {
        super();
        this.command = command;
    }

    public void execute(TaskList taskList, Storage storage, Ui ui) throws NyxException {
        try {
            String[] splitInput = this.command.split(" ");
            int taskIndex = Integer.parseInt(splitInput[1]) - 1;
            taskList.markTaskAsComplete(taskIndex, ui);
            storage.saveTaskData(taskList.toSaveFormat());
        } catch (Exception e) {
            throw new InvalidUsageException("Wrong usage. Correct usage: mark [task id]");
        }
    }
}
