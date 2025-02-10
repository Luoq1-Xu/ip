package nyx.tasks;

/**
 * The Task class represents a generic task.
 * It provides methods to mark the task as complete or incomplete, and to get the task's details.
 */
public abstract class Task {
    private final String name;
    private boolean completed = false;

    /**
     * Constructs a new Task instance with the specified name.
     *
     * @param name The name of the task.
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     * Marks the task as complete.
     */
    public void markAsComplete() {
        this.completed = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void markAsIncomplete() {
        this.completed = false;
    }

    /**
     * Returns the task type as a string.
     *
     * @return The task type.
     */
    public abstract String getTaskType();

    /**
     * Returns the completed status of the task.
     *
     * @return 1 if the task is completed, 0 otherwise.
     */
    public int isCompleted() {
        return completed ? 1 : 0;
    }

    /**
     * Returns the name of the task.
     *
     * @return The name of the task.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a string representation of the task in a format suitable for saving to a file.
     *
     * @return A string representing the task in a save format.
     */
    public String toSaveFormat() {
        return this.getTaskType() + " | " + this.isCompleted() + " | " + this.getName();
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representing the task.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.completed) {
            sb.append("[X]");
        } else {
            sb.append("[ ]");
        }
        sb.append(" ");
        sb.append(this.name);
        return sb.toString();
    }
}
