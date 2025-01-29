import exceptions.NyxException;

public class ByeCommand extends Command {
    public void execute(TaskList taskList, Storage storage, Ui ui) throws NyxException {
        ui.displayString("Goodbye!");
    }
}
