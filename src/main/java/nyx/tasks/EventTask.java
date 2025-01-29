package nyx.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that is an event with a start and end date.
 */
public class EventTask extends Task {

    private final LocalDate start;
    private final LocalDate end;

    /**
     * Constructs a new EventTask instance with the specified name, start date, and end date.
     *
     * @param name  The name of the event.
     * @param start The start date of the event.
     * @param end   The end date of the event.
     */
    public EventTask(String name, LocalDate start, LocalDate end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the task type as a string.
     *
     * @return The task type, which is "E" for EventTask.
     */
    public String getTaskType() {
        return "E";
    }

    /**
     * Returns a string representation of the task in a format suitable for saving to a file.
     *
     * @return A string representing the task in a save format.
     */
    @Override
    public String toSaveFormat() {
        return super.toSaveFormat() + " | " + this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " | " + this.end.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representing the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " Start: "
                + this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " End: "
                + this.end.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}