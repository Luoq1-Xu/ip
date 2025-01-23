import exceptions.InvalidUsageException;
import exceptions.NyxException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Nyx {

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

    public static void main(String[] args) {
        System.out.println("\n" + logo + "\n");
        System.out.println(divider);
        System.out.println("Hello. I am Nyx.\n");
        System.out.println("What can I do for you?\n" + divider);

        while (true) {
            String input = scan.nextLine();
            String result = parseCommand(input);
            if (result.equals("bye")) {
                // Exit
                break;
            }
            if (result.equals("success")) {
                // Successfully executed command
                System.out.println(divider);
                continue;
            } else {
                // Error occurred, print error message
                System.out.println(result);
                System.out.println(divider);
            }
        }
    }

    public static String parseCommand(String input) {
        if (input.startsWith("todo ")) {
            try {
                handleTodo(input);
                return "success";
            } catch (NyxException e) {
                return e.getMessage();
            }
        } else if (input.startsWith("deadline ")) {
            try {
                handleDeadline(input);
                return "success";
            } catch (NyxException e) {
                return e.getMessage();
            }
        } else if (input.startsWith("event ")) {
            try {
                handleEvent(input);
                return "success";
            } catch (NyxException e) {
                return e.getMessage();
            }
        } else if (input.equals("list")) {
            System.out.println("Here is the current list of tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
            return "success";
        } else if (input.equals("bye")) {
            System.out.println("Goodbye!");
            return "bye";
        } else if (input.startsWith("mark ")) {
            try {
                mark(input);
                return "success";
            } catch (NyxException e) {
                return e.getMessage();
            }
        } else if (input.startsWith("unmark ")) {
            try {
                unMark(input);
                return "success";
            } catch (NyxException e) {
                return e.getMessage();
            }
        } else if (input.startsWith("delete ")) {
            try {
                deleteTask(input);
                return "success";
            } catch (NyxException e) {
                return e.getMessage();
            }
        }
        return "Unrecognised command.";
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

}
