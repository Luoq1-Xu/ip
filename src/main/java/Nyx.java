import exceptions.InvalidUsageException;
import exceptions.NyxException;
import exceptions.UnknownCommandException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Nyx {

    private static final Path FILE_PATH = Paths.get("data", "tasks.txt");

    public enum CommandType {
        TODO,
        DEADLINE,
        EVENT,
        MARK,
        UNMARK,
        DELETE,
        LIST,
        BYE,
        UNKNOWN
    }

    static ArrayList<Task> tasks = new ArrayList<>();
    static HashMap<String, Integer> taskNameToId = new HashMap<>();
    static Scanner scan = new Scanner(System.in);
    static String divider = "____________________________________________________________";
    static String logo = """
                ███▄▄▄▄   ▄██   ▄   ▀████    ▐████▀\s
                ███▀▀▀██▄ ███   ██▄   ███▌   ████▀ \s
                ███   ███ ███▄▄▄███    ███  ▐███   \s
                ███   ███ ▀▀▀▀▀▀███    ▀███▄███▀   \s
                ███   ███ ▄██   ███    ████▀██▄    \s
                ███   ███ ███   ███   ▐███  ▀███   \s
                ███   ███ ███   ███  ▄███     ███▄ \s
                 ▀█   █▀   ▀█████▀  ████       ███▄\s
                                                   \s""";
    static int counter = 1;
    static boolean isRunning = true;

    public static void main(String[] args) {
        System.out.println("\n" + logo + "\n");
        System.out.println(divider);
        System.out.println("Hello. I am Nyx.\n");
        System.out.println("What can I do for you?\n" + divider);

        loadTaskData();

        while (isRunning) {
            String input = scan.nextLine();
            CommandType command = getCommandType(input);
            try {
                executeCommand(command, input);
            } catch (NyxException e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println(divider);
            }
        }
    }

    public static CommandType getCommandType(String input) {
        if (input.startsWith("todo ")) {
            return CommandType.TODO;
        } else if (input.startsWith("deadline ")) {
            return CommandType.DEADLINE;
        } else if (input.startsWith("event ")) {
            return CommandType.EVENT;
        } else if (input.equals("list")) {
            return CommandType.LIST;
        } else if (input.equals("bye")) {
            return CommandType.BYE;
        } else if (input.startsWith("mark ")) {
            return CommandType.MARK;
        } else if (input.startsWith("unmark ")) {
            return CommandType.UNMARK;
        } else if (input.startsWith("delete ")) {
            return CommandType.DELETE;
        } else {
            return CommandType.UNKNOWN;
        }
    }

    public static void executeCommand(CommandType command, String input) throws NyxException {
        if (command != CommandType.UNKNOWN) {
            switch (command) {
            case TODO:
                handleTodo(input);
                saveTaskData();
                break;
            case DEADLINE:
                handleDeadline(input);
                saveTaskData();
                break;
            case EVENT:
                handleEvent(input);
                saveTaskData();
                break;
            case LIST:
                System.out.println("Here is the current list of tasks:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
                break;
            case BYE:
                isRunning = false;
                System.out.println("Goodbye!");
                break;
            case MARK:
                mark(input);
                saveTaskData();
                break;
            case UNMARK:
                unMark(input);
                saveTaskData();
                break;
            case DELETE:
                deleteTask(input);
                saveTaskData();
                break;
            }
        } else {
            throw new UnknownCommandException("Unrecognised command.");
        }
    }

    public static void handleTodo(String rawInput) throws NyxException {
        try {
            String args = rawInput.substring(5);
            TodoTask newTask = new TodoTask(args, counter);
            tasks.add(newTask);
            taskNameToId.put(args, counter);
            counter++;
            System.out.println("Task created: " + newTask + "\n");
            System.out.println("There are currently " + tasks.size() + " tasks.");
        } catch (Exception e) {
            throw new InvalidUsageException("Wrong Usage. Correct usage: todo [task name] ");
        }
    }

    public static void handleDeadline(String rawInput) throws NyxException {
        try {
            String parts = rawInput.substring(9);
            String[] splitParts = parts.split(" -by ");
            String taskName = splitParts[0].trim();
            String deadline = splitParts[1];
            DeadlineTask newTask = new DeadlineTask(taskName, counter, deadline);
            tasks.add(newTask);
            taskNameToId.put(taskName, counter);
            counter++;
            System.out.println("Task created: " + newTask + "\n");
            System.out.println("There are currently " + tasks.size() + " tasks.");
        } catch (Exception e) {
            throw new InvalidUsageException("Wrong Usage. Correct usage: deadline [task name] -by [deadline]");
        }
    }

    public static void handleEvent(String rawInput) throws NyxException {
        try {
            String args = rawInput.substring(6);
            String[] parts = args.split(" -start | -end ");
            String eventName = parts[0].trim();
            String startTime = parts[1].trim();
            String endTime = parts[2].trim();
            EventTask newEvent = new EventTask(eventName, counter, startTime, endTime);
            tasks.add(newEvent);
            taskNameToId.put(eventName, counter);
            counter++;
            System.out.println("Event created: " + newEvent + "\n");
            System.out.println("There are currently " + tasks.size() + " tasks.");
        } catch (Exception e) {
            throw new InvalidUsageException("Wrong usage. Correct usage: event [event name] -start [time] -end [time]");
        }
    }

    public static void mark(String command) throws NyxException {
        try {
            String[] splitInput = command.split(" ");
            int taskIndex = Integer.parseInt(splitInput[1]) - 1;
            if (taskIndex < tasks.size() && tasks.get(taskIndex) != null) {
                tasks.get(taskIndex).markAsComplete();
                System.out.println("Task marked as complete: " + tasks.get(taskIndex) + "\n");
            } else {
                System.out.println("Invalid task number.\n");
            }
        } catch (Exception e) {
            throw new InvalidUsageException("Wrong usage. Correct usage: mark [task id]");
        }
    }

    public static void unMark(String command) throws NyxException {
        try {
            String[] splitInput = command.split(" ");
            int taskIndex = Integer.parseInt(splitInput[1]) - 1;
            if (taskIndex < tasks.size() && tasks.get(taskIndex) != null) {
                tasks.get(taskIndex).markAsIncomplete();
                System.out.println("Task marked as complete: " + tasks.get(taskIndex) + "\n");
            } else {
                System.out.println("Invalid task number.\n");
            }
        } catch (Exception e) {
            throw new InvalidUsageException("Wrong usage. Correct usage: mark [task id]");
        }
    }

    public static void deleteTask(String command) throws NyxException {
        try {
            String[] splitInput = command.split(" ");
            int taskIndex = Integer.parseInt(splitInput[1]) - 1;
            if (taskIndex < tasks.size() && tasks.get(taskIndex) != null) {
                Task taskToDelete = tasks.get(taskIndex);
                tasks.remove(taskIndex);
                System.out.println("Task deleted: " + taskToDelete);
                System.out.println("There are now " + tasks.size() + " tasks.");
            } else {
                System.out.println("Invalid task number.\n");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("bruh");
            throw new InvalidUsageException("Wrong usage. Correct usage: delete [task id]");
        }
    }

    public static void loadTaskData() {
        try {
            // Ensure the directories exist
            if (Files.notExists(FILE_PATH.getParent())) {
                Files.createDirectories(FILE_PATH.getParent());
            }

            // Create the file if it doesn't exist
            if (Files.notExists(FILE_PATH)) {
                Files.createFile(FILE_PATH);
                return; // No data to load yet
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH.toFile()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] splitInput = line.split(" \\| ");
                    String taskType = splitInput[0];
                    boolean isDone = splitInput[1].equals("1");
                    String taskName = splitInput[2];
                    switch (taskType) {
                    case "T":
                        TodoTask todoTask = new TodoTask(taskName, counter);
                        if (isDone) todoTask.markAsComplete();
                        tasks.add(todoTask);
                        taskNameToId.put(taskName, counter);
                        break;
                    case "D":
                        String deadline = splitInput[3];
                        DeadlineTask deadlineTask = new DeadlineTask(taskName, counter, deadline);
                        if (isDone) deadlineTask.markAsComplete();
                        tasks.add(deadlineTask);
                        taskNameToId.put(taskName, counter);
                        break;
                    case "E":
                        String start = splitInput[3];
                        String end = splitInput[4];
                        EventTask eventTask = new EventTask(taskName, counter, start, end);
                        if (isDone) eventTask.markAsComplete();
                        tasks.add(eventTask);
                        taskNameToId.put(taskName, counter);
                        break;
                    default:
                        // Ignore malformed lines
                        // To ensure counter does not increment on invalid line
                        counter -= 1;
                        break;
                    }
                    counter++;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void saveTaskData() {
        try {
            // Ensure the directories exist
            if (Files.notExists(FILE_PATH.getParent())) {
                Files.createDirectories(FILE_PATH.getParent());
            }

            // Create or overwrite file
            try (FileWriter fileWriter = new FileWriter(FILE_PATH.toFile())) {
                for (Task task : tasks) {
                    fileWriter.write(task.toSaveFormat() + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
