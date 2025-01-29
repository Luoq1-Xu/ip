import exceptions.NyxException;

public class Nyx {

    private final Storage storage;
    private final TaskList taskList;
    private final Ui ui;

    public Nyx() {
        storage = new Storage();
        ui = new Ui();
        taskList = new TaskList(storage.loadTaskData());
    }

    public void run() {
        ui.displayWelcome();
        boolean isRunning = true;
        while (isRunning) {
            try {
                String command = ui.readCommand();
                Command c = Parser.parse(command);
                c.execute(taskList, storage, ui);
                if (c instanceof ByeCommand) {
                    isRunning = false;
                }
            } catch (NyxException e) {
                ui.displayString(e.getMessage());
            } finally {
                ui.displayDivider(); // Divider line
            }
        }
    }

    public static void main(String[] args) {
        new Nyx().run();
    }
}
