package nyx.tasks;

import java.util.ArrayList;

/**
 * The Task class represents a generic task.
 * It provides methods to mark the task as complete or incomplete, and to get the task's details.
 */
public abstract class Task {
    private final String name;
    private boolean isCompleted = false;
    private final ArrayList<String> tags;

    /**
     * Constructs a new Task instance with the specified name.
     *
     * @param name The name of the task.
     */
    public Task(String name) {
        this.name = name;
        this.tags = new ArrayList<>();
    }

    /**
     * Marks the task as complete.
     */
    public void markAsComplete() {
        this.isCompleted = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void markAsIncomplete() {
        this.isCompleted = false;
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
        return isCompleted ? 1 : 0;
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
        // Create a comma-separated string of tags; if empty, it remains blank.
        String tagsFormatted = String.join(",", tags);

        // Format the output with a consistent delimiter for easy parsing.
        return String.format("%s | %d | %s | %s",
                getTaskType(), isCompleted(), getName(), tagsFormatted);
    }

    /**
     * Adds a tag to the current task.
     */
    public void addTag(String tag) {
        this.tags.add(tag);
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representing the task.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.isCompleted) {
            sb.append("[X]");
        } else {
            sb.append("[ ]");
        }
        sb.append(" ");
        sb.append(this.name);
        sb.append(" ");

        for (String s : this.tags) {
            String tag = "#" + s + " ";
            sb.append(tag);
        }

        return sb.toString();
    }
}
