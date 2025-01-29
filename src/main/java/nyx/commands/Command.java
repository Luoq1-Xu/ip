package nyx.commands;

import nyx.exceptions.NyxException;
import nyx.Storage;
import nyx.TaskList;
import nyx.Ui;

public abstract class Command {
    public abstract void execute(TaskList taskList, Storage storage, Ui ui) throws NyxException;
}
