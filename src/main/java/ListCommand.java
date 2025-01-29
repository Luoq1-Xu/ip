import exceptions.NyxException;

public class ListCommand extends Command {
    public void execute(TaskList taskList, Storage storage, Ui ui) throws NyxException {
        ui.displayString(taskList.getTaskList());
    }
}
