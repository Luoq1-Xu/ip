package nyx.commands;

import nyx.exceptions.NyxException;
import nyx.Storage;
import nyx.TaskList;
import nyx.Ui;

public class ListCommand extends Command {
    public void execute(TaskList taskList, Storage storage, Ui ui) throws NyxException {
        ui.displayString(taskList.getTaskList());
    }
}
