package nyx.commands;

import nyx.exceptions.NyxException;
import nyx.exceptions.UnknownCommandException;
import nyx.Storage;
import nyx.TaskList;
import nyx.Ui;

public class UnknownCommand extends Command {
    public void execute(TaskList taskList, Storage storage, Ui ui) throws NyxException {
        throw new UnknownCommandException("Unrecognized command");
    }
}
