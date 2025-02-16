package nyx;

import nyx.commands.ByeCommand;
import nyx.commands.Command;
import nyx.exceptions.NyxException;

/**
 * The main class for the Nyx chatbot application.
 * It initializes the necessary components and runs the main loop to process user commands.
 */
@SuppressWarnings("checkstyle:Regexp")
public class Nyx {

    private final Storage storage;
    private final TaskList taskList;
    private final Ui ui;

    /**
     * Constructs a new Nyx instance.
     * Initializes the storage, UI, and task list components.
     */
    public Nyx() {
        storage = new Storage();
        ui = new Ui();
        taskList = new TaskList(storage.loadTaskData());
    }

    /**
     * Runs the main loop of the Nyx chatbot.
     * Displays the welcome message and processes user commands until the "bye" command is received.
     */
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
        ui.cleanup(); // Clean up before exiting
    }

    /**
     * Starts the Nyx chatbot application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Nyx().run();
    }

    /**
     * The main method to start the Nyx chatbot application.
     *
     * @param input The input entered by the user.
     * @return      The output or error generated after executing the command.
     */
    public String getResponse(String input) {
        Command c = Parser.parse(input);
        try {
            String response = c.execute(taskList, storage, ui);
            assert response != null : "response should be not null";
            return response;
        } catch (NyxException e) {
            return e.getMessage();
        }
    }
}
