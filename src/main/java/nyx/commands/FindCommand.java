package nyx.commands;

import nyx.Storage;
import nyx.TaskList;
import nyx.Ui;
import nyx.exceptions.NyxException;

/**
 * Represents a command that searches for tasks in the task list that contain a specified query string.
 * The search query is extracted from the user input.
 */
public class FindCommand extends Command {
    private final String query;

    public FindCommand(String input) {
        this.query = input.substring(5).trim();
    }

    public void execute(TaskList taskList, Storage storage, Ui ui) throws NyxException {
        taskList.findMatchingTasks(this.query, ui);
    }
}
