package nyx;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import nyx.tasks.DeadlineTask;
import nyx.tasks.EventTask;
import nyx.tasks.Task;
import nyx.tasks.TodoTask;

/**
 * The Storage class handles loading and saving task data to and from a file.
 */
public class Storage {
    private static final Path FILE_PATH = Paths.get("data", "tasks.txt");
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("MMM d yyyy");

    /**
     * Loads task data from the file.
     * If the file or directories do not exist, they are created.
     *
     * @return An ArrayList of tasks loaded from the file.
     */
    public ArrayList<Task> loadTaskData() {
        try {
            ArrayList<Task> tasks = new ArrayList<>();

            // Ensure the directories exist
            if (Files.notExists(FILE_PATH.getParent())) {
                Files.createDirectories(FILE_PATH.getParent());
            }

            // Create the file if it doesn't exist
            if (Files.notExists(FILE_PATH)) {
                Files.createFile(FILE_PATH);
                return tasks; // No data to load yet
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH.toFile()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    Task task = parseTask(line);
                    if (task != null) {
                        tasks.add(task);
                    }
                }
            }

            return tasks;

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }

    /**
     * Saves task data to the file.
     * If the directory and necessary file do not exist, they are created.
     *
     * @param toSave The string representation of the tasks to save.
     */
    public void saveTaskData(String toSave) {
        try {
            // Ensure the directories exist
            if (Files.notExists(FILE_PATH.getParent())) {
                Files.createDirectories(FILE_PATH.getParent());
            }

            // Create or overwrite file
            try (FileWriter fileWriter = new FileWriter(FILE_PATH.toFile())) {
                fileWriter.write(toSave);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Parses a line of text into a Task instance, using the save format generated
     * by Task#toSaveFormat(). The base save format is:
     * taskType | isCompleted (0/1) | taskName | tags
     * DeadlineTask adds:
     *  | deadline
     * EventTask adds:
     *  | startDate | endDate
     *
     * @param line the line to parse.
     * @return a Task instance, or null if parsing fails.
     */
    public static Task parseTask(String line) {
        String[] tokens = line.split(" \\| ", -1);
        if (tokens.length < 4) {
            return null;
        }

        String taskType = tokens[0];
        boolean isDone = tokens[1].equals("1");
        String taskName = tokens[2];
        String tagsToken = tokens[3];

        Task task;

        switch (taskType) {
        case "T":
            task = new TodoTask(taskName);
            break;
        case "D":
            if (tokens.length < 5) {
                return null;
            }
            String deadlineString = tokens[4];
            LocalDate deadline = LocalDate.parse(deadlineString, DATE_FORMATTER);
            task = new DeadlineTask(taskName, deadline);
            break;
        case "E":
            if (tokens.length < 6) {
                return null;
            }
            String startString = tokens[4];
            String endString = tokens[5];
            LocalDate startDate = LocalDate.parse(startString, DATE_FORMATTER);
            LocalDate endDate = LocalDate.parse(endString, DATE_FORMATTER);
            task = new EventTask(taskName, startDate, endDate);
            break;
        default:
            return null;
        }

        // Set completion status
        if (isDone) {
            task.markAsComplete();
        }

        // Parse and add tags if available (tags are comma-separated)
        if (!tagsToken.isEmpty()) {
            String[] tags = tagsToken.split(",");
            for (String tag : tags) {
                task.addTag(tag);
            }
        }

        return task;
    }
}
