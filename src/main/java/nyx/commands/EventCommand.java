package nyx.commands;

import java.time.LocalDate;

import nyx.Storage;
import nyx.TaskList;
import nyx.Ui;
import nyx.exceptions.InvalidUsageException;
import nyx.exceptions.NyxException;
import nyx.tasks.EventTask;

/**
 * Represents a command to add an event task.
 */
public class EventCommand extends Command {

    private final String command;

    /**
     * Constructs a new EventCommand instance with the specified input command.
     *
     * @param input The input command string.
     */
    public EventCommand(String input) {
        super();
        this.command = input;
    }

    /**
     * Executes the EventCommand, adding an event task to the task list.
     *
     * @param taskList The task list.
     * @param storage  The storage handler.
     * @param ui       The user interface handler.
     * @return     The output string to be displayed.
     * @throws NyxException If an error occurs during execution.
     */
    public String execute(TaskList taskList, Storage storage, Ui ui) throws NyxException {
        try {
            EventTask newTask = getEventTask();
            String output = taskList.addTask(newTask);
            storage.saveTaskData(taskList.toSaveFormat());
            return output;
        } catch (Exception e) {
            throw new InvalidUsageException("Wrong usage. Correct usage: event [event name] -start [time] -end [time]\n"
                    + "Dates should be in YYYY-MM-DD format. For example: 2025-02-19");
        }
    }

    /**
     * Parses the input command to create an EventTask.
     *
     * @return The created EventTask.
     */
    private EventTask getEventTask() {
        String args = this.command.substring(6);
        String[] parts = args.split(" -start | -end ");
        String eventName = parts[0].trim();
        String startTimeString = parts[1].trim();
        String endTimeString = parts[2].trim();

        // Parse start and end times
        LocalDate startTime = LocalDate.parse(startTimeString);
        LocalDate endTime = LocalDate.parse(endTimeString);

        return new EventTask(eventName, startTime, endTime);
    }
}
