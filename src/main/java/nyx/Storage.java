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

            // Define the formatter for the expected date format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");

            try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH.toFile()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] splitInput = line.split(" \\| ");
                    String taskType = splitInput[0];
                    boolean isDone = splitInput[1].equals("1");
                    String taskName = splitInput[2];
                    switch (taskType) {
                    case "T":
                        TodoTask todoTask = new TodoTask(taskName);
                        if (isDone) {
                            todoTask.markAsComplete();
                        }
                        tasks.add(todoTask);
                        break;
                    case "D":
                        String deadlineString = splitInput[3];
                        String normalizedDate = normalizeDateString(deadlineString);
                        LocalDate deadline = LocalDate.parse(normalizedDate, formatter);
                        DeadlineTask deadlineTask = new DeadlineTask(taskName, deadline);
                        if (isDone) {
                            deadlineTask.markAsComplete();
                        }
                        tasks.add(deadlineTask);
                        break;
                    case "E":
                        String startString = splitInput[3];
                        String endString = splitInput[4];
                        String normalizedStart = normalizeDateString(startString);
                        String normalizedEnd = normalizeDateString(endString);
                        LocalDate startTime = LocalDate.parse(normalizedStart, formatter);
                        LocalDate endTime = LocalDate.parse(normalizedEnd, formatter);
                        EventTask eventTask = new EventTask(taskName, startTime, endTime);
                        if (isDone) {
                            eventTask.markAsComplete();
                        }
                        tasks.add(eventTask);
                        break;
                    default:
                        // Ignore malformed lines
                        break;
                    }
                }
                return tasks;
            }
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
     * Helper method to normalize the date string.
     * Ensures the day part of the date is always two digits.
     *
     * @param dateString The date string to normalize.
     * @return The normalized date string.
     */
    private static String normalizeDateString(String dateString) {
        String[] parts = dateString.split(" ");
        if (parts[1].length() == 1) {
            parts[1] = "0" + parts[1]; // Add leading zero to day part
        }
        return String.join(" ", parts);
    }
}
