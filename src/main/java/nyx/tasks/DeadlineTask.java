package nyx.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */
public class DeadlineTask extends Task {

    private final LocalDate deadline;

    /**
     * Constructs a new DeadlineTask instance with the specified name and deadline.
     *
     * @param name     The name of the task.
     * @param deadline The deadline of the task.
     */
    public DeadlineTask(String name, LocalDate deadline) {
        super(name);
        this.deadline = deadline;
    }

    /**
     * Returns the task type as a string.
     *
     * @return The task type, which is "D" for DeadlineTask.
     */
    public String getTaskType() {
        return "D";
    }

    /**
     * Returns a string representation of the task in a format suitable for saving to a file.
     *
     * @return A string representing the task in a save format.
     */
    @Override
    public String toSaveFormat() {
        return super.toSaveFormat() + " | " + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representing the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " Deadline: " + this.deadline;
    }
}
