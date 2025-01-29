import exceptions.InvalidUsageException;
import exceptions.NyxException;

public class DeleteCommand extends Command {
    private final String command;

    public DeleteCommand(String command) {
        super();
        this.command = command;
    }

    public void execute(TaskList taskList, Storage storage, Ui ui) throws NyxException {
        try {
            String[] splitInput = this.command.split(" ");
            int taskIndex = Integer.parseInt(splitInput[1]) - 1;
            taskList.deleteTask(taskIndex, ui);
            storage.saveTaskData(taskList.toSaveFormat());
        } catch (Exception e) {
            throw new InvalidUsageException("Wrong usage. Correct usage: delete [task id]");
        }
    }
}
