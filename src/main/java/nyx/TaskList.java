package nyx;

import java.util.ArrayList;

import nyx.tasks.Task;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public String getTaskList() {
        StringBuilder sb = new StringBuilder();
        sb.append("Here is the current list of tasks:\n");
        for (int i = 0; i < tasks.size(); i++) {
            String taskRepresentation = (i + 1) + ". " + tasks.get(i) + "\n";
            sb.append(taskRepresentation);
        }
        return sb.toString();
    }

    public String toSaveFormat() {
        StringBuilder sb = new StringBuilder();
        for (Task task : tasks) {
            String line = task.toSaveFormat() + System.lineSeparator();
            sb.append(line);
        }
        return sb.toString();
    }

    public void addTask(Task task, Ui ui) {
        tasks.add(task);
        String output = "Task created: " + task + "\n" + "There are currently " + tasks.size() + " tasks.";
        ui.displayString(output);
    }

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

    public void markTaskAsComplete(int taskIndex, Ui ui) {
        if (taskIndex < tasks.size() && tasks.get(taskIndex) != null) {
            tasks.get(taskIndex).markAsComplete();
            String output = "Task marked as complete: " + tasks.get(taskIndex) + "\n";
            ui.displayString(output);
        } else {
            ui.displayString("Invalid task number.\n");
        }
    }

    public void markTaskAsIncomplete(int taskIndex, Ui ui) {
        if (taskIndex < tasks.size() && tasks.get(taskIndex) != null) {
            tasks.get(taskIndex).markAsIncomplete();
            String output = "Task marked as incomplete: " + tasks.get(taskIndex) + "\n";
            ui.displayString(output);
        } else {
            ui.displayString("Invalid task number.\n");
        }
    }


}
