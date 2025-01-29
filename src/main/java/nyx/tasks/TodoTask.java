package nyx.tasks;

/**
 * The TodoTask class represents a task that needs to be done.
 */
public class TodoTask extends Task {

    /**
     * Constructs a new TodoTask instance with the specified name.
     *
     * @param name The name of the task.
     */
    public TodoTask(String name) {
        super(name);
    }

    /**
     * Returns the task type as a string.
     *
     * @return The task type, which is "T" for TodoTask.
     */
    public String getTaskType() {
        return "T";
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representing the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}