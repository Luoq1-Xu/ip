package nyx;

import java.util.ArrayList;

import nyx.tasks.Task;

/**
 * The TaskList class manages a list of tasks.
 * It provides methods to add, delete, and mark tasks as complete or incomplete.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs a new TaskList instance with the specified list of tasks.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns a string representation of the current list of tasks.
     *
     * @return A string representing the current list of tasks.
     */
    public String getTaskList() {
        StringBuilder sb = new StringBuilder();
        sb.append("Here is the current list of tasks:\n");
        for (int i = 0; i < tasks.size(); i++) {
            String taskRepresentation = (i + 1) + ". " + tasks.get(i) + "\n";
            sb.append(taskRepresentation);
        }
        return sb.toString();
    }

    /**
     * Returns a string representation of the tasks in a format suitable for saving to a file.
     *
     * @return A string representing the tasks in a save format.
     */
    public String toSaveFormat() {
        StringBuilder sb = new StringBuilder();
        for (Task task : tasks) {
            String line = task.toSaveFormat() + System.lineSeparator();
            sb.append(line);
        }
        return sb.toString();
    }

    /**
     * Adds a task to the task list and displays a message using the specified UI.
     *
     * @param task The task to add.
     * @param ui   The UI to use for displaying messages.
     */
    public void addTask(Task task, Ui ui) {
        tasks.add(task);
        String output = "Task created: " + task + "\n" + "There are currently " + tasks.size() + " tasks.";
        ui.displayString(output);
    }

    /**
     * Deletes a task from the task list at the specified index and displays a message using the specified UI.
     *
     * @param taskIndex The index of the task to delete.
     * @param ui        The UI to use for displaying messages.
     */
    public void deleteTask(int taskIndex, Ui ui) {
        if (taskIndex < tasks.size() && tasks.get(taskIndex) != null) {
            Task taskToDelete = tasks.get(taskIndex);
            tasks.remove(taskIndex);
            String output = "Task deleted: " + taskToDelete + "\n" + "There are currently " + tasks.size() + " tasks.";
            ui.displayString(output);
        } else {
            ui.displayString("Invalid task number.");
        }
    }

    /**
     * Marks a task as complete at the specified index and displays a message using the specified UI.
     *
     * @param taskIndex The index of the task to mark as complete.
     * @param ui        The UI to use for displaying messages.
     */
    public void markTaskAsComplete(int taskIndex, Ui ui) {
        if (taskIndex < tasks.size() && tasks.get(taskIndex) != null) {
            tasks.get(taskIndex).markAsComplete();
            String output = "Task marked as complete: " + tasks.get(taskIndex) + "\n";
            ui.displayString(output);
        } else {
            ui.displayString("Invalid task number.\n");
        }
    }

    /**
     * Marks a task as incomplete at the specified index and displays a message using the specified UI.
     *
     * @param taskIndex The index of the task to mark as incomplete.
     * @param ui        The UI to use for displaying messages.
     */
    public void markTaskAsIncomplete(int taskIndex, Ui ui) {
        if (taskIndex < tasks.size() && tasks.get(taskIndex) != null) {
            tasks.get(taskIndex).markAsIncomplete();
            String output = "Task marked as incomplete: " + tasks.get(taskIndex) + "\n";
            ui.displayString(output);
        } else {
            ui.displayString("Invalid task number.\n");
        }
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int getTaskCount() {
        return tasks.size();
    }

    public void findMatchingTasks(String search, Ui ui) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");
        int matchingTasks = 0;
        for (Task task : tasks) {
            String taskString = task.toString();
            if (taskString.contains(search)) {
                matchingTasks++;
                sb.append(taskString).append("\n");
            }
        }
        if (matchingTasks == 0) {
            ui.displayString("No matching tasks found.");
            return;
        }
        ui.displayString(sb.toString());
    }

}