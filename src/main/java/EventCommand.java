import java.time.LocalDate;

import exceptions.InvalidUsageException;
import exceptions.NyxException;

public class EventCommand extends Command{

    private final String command;

    public EventCommand(String input) {
        this.command = input;
    }

    public void execute(TaskList taskList, Storage storage, Ui ui) throws NyxException {
        try {
            EventTask newTask = getEventTask();
            taskList.addTask(newTask, ui);
            storage.saveTaskData(taskList.toSaveFormat());
        } catch (Exception e) {
            throw new InvalidUsageException("Wrong usage. Correct usage: event [event name] -start [time] -end [time]\n"
                    + "Dates should be in YYYY-MM-DD format. For example: 2025-02-19");
        }
    }

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
