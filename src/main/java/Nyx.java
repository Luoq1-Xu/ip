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
                break;
            }
            if (result.equals("success")) {
                System.out.println(divider);
                continue;
            }
        }
    }

    public static String parseCommand(String input) {
        if (input.startsWith("todo ")) {
            String args = input.substring(5);
            TodoTask newTask = new TodoTask(args, counter);
            tasks.add(newTask);
            taskNameToId.put(args, counter);
            counter++;
            System.out.println("Task created: " + newTask + "\n");
            System.out.println("There are currently " + tasks.size() + " tasks.");
            return "success";
        } else if (input.startsWith("deadline ")) {
            String parts = input.substring(9);
            String[] splitParts = parts.split(" -by ");
            if (splitParts.length == 2) {
                String taskName = splitParts[0].trim();
                String deadline = splitParts[1];
                DeadlineTask newTask = new DeadlineTask(taskName, counter, deadline);
                tasks.add(newTask);
                taskNameToId.put(taskName, counter);
                counter++;
                System.out.println("Task created: " + newTask + "\n");
                System.out.println("There are currently " + tasks.size() + " tasks.");
                return "success";
            }
            return "Wrong usage. Correct usage: deadline [task name] -by [deadline]\n";
        } else if (input.startsWith("event ")) {
            String args = input.substring(6);
            String[] parts = args.split(" -start | -end ");
            if (parts.length > 3 || !parts[0].isEmpty() || !parts[1].isEmpty() || !parts[2].isEmpty()) {
                String eventName = parts[0].trim();
                String startTime = parts[1].trim();
                String endTime = parts[2].trim();
                EventTask newEvent = new EventTask(eventName, counter, startTime, endTime);
                tasks.add(newEvent);
                taskNameToId.put(eventName, counter);
                counter++;
                System.out.println("Event created: " + newEvent + "\n");
                System.out.println("There are currently " + tasks.size() + " tasks.");
                return "success";
            }
            return "Wrong usage. Correct usage: event [event name] -start [time] -end [time]\n";
        } else if (input.equals("list")) {
            System.out.println("Here is the current list of tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        } else if (input.equals("bye")) {
            System.out.println("Goodbye!");
            return "bye";
        } else if (input.startsWith("mark ")) {
            String[] splitInput = input.split(" ");
            if (splitInput.length == 2) {
                int taskIndex = Integer.parseInt(splitInput[1]) - 1;
                if (taskIndex < tasks.size() && tasks.get(taskIndex) != null) {
                    tasks.get(taskIndex).markAsComplete();
                    System.out.println("Task marked as complete: " + tasks.get(taskIndex) + "\n");
                }
            } else {
                return "Wrong usage. Correct usage: mark [task id]\n";
            }
        } else if (input.startsWith("unmark ")) {
            String[] splitInput = input.split(" ");
            if (splitInput.length == 2) {
                int taskIndex = Integer.parseInt(splitInput[1]) - 1;
                if (taskIndex < tasks.size() && tasks.get(taskIndex) != null) {
                    tasks.get(taskIndex).markAsIncomplete();
                    System.out.println("Task marked as incomplete: " + tasks.get(taskIndex) + "\n");
                }
            } else {
                return "Wrong usage. Correct usage: unmark [task id]\n";
            }
        }
        return "success";
    }
}
