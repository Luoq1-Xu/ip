import exceptions.NyxException;
import exceptions.UnknownCommandException;

public class UnknownCommand extends Command {
    public void execute(TaskList taskList, Storage storage, Ui ui) throws NyxException {
        throw new UnknownCommandException("Unrecognized command");
    }
}
