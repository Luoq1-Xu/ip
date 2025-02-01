package nyx;

import java.util.ArrayList;

import nyx.tasks.Task;

/**
 * The TaskList class manages a list of tasks.
 * It provides methods to add, delete, and mark tasks as complete or incomplete.
 */
@SuppressWarnings("checkstyle:Regexp")
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
     * @return     The output string to be displayed.
     */
    public String addTask(Task task) {
        tasks.add(task);
        return "Task created: " + task + "\n" + "There are currently " + tasks.size() + " tasks.";
    }

    /**
     * Deletes a task from the task list at the specified index and displays a message using the specified UI.
     *
     * @param taskIndex The index of the task to delete.
     * @return     The output string to be displayed.
     */
    public String deleteTask(int taskIndex) {
        if (taskIndex < tasks.size() && tasks.get(taskIndex) != null) {
            Task taskToDelete = tasks.get(taskIndex);
            tasks.remove(taskIndex);
            return "Task deleted: " + taskToDelete + "\n" + "There are currently " + tasks.size() + " tasks.";
        } else {
            return "Invalid task number.";
        }
    }

    /**
     * Marks a task as complete at the specified index and displays a message using the specified UI.
     *
     * @param taskIndex The index of the task to mark as complete.
     * @return          The output string to be displayed.
     */
    public String markTaskAsComplete(int taskIndex) {
        if (taskIndex < tasks.size() && tasks.get(taskIndex) != null) {
            tasks.get(taskIndex).markAsComplete();
            return "Task marked as complete: " + tasks.get(taskIndex) + "\n";
        } else {
            return "Invalid task number.\n";
        }
    }

    /**
     * Marks a task as incomplete at the specified index and displays a message using the specified UI.
     *
     * @param taskIndex The index of the task to mark as incomplete.
     * @return the output after executing the command.
     */
    public String markTaskAsIncomplete(int taskIndex) {
        if (taskIndex < tasks.size() && tasks.get(taskIndex) != null) {
            tasks.get(taskIndex).markAsIncomplete();
            return "Task marked as incomplete: " + tasks.get(taskIndex) + "\n";
        } else {
            return "Invalid task number.\n";
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

    /**
     * Searches for tasks that contain the given search string and
     * displays them using the provided {@code Ui} instance.
     *
     * @param search The string to search for in the task list.
     * @return       The output string to be displayed.
     */
    public String findMatchingTasks(String search) {
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
            return "No matching tasks found.";
        }
        return sb.toString();
    }
}
