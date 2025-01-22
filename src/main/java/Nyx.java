import java.util.HashMap;
import java.util.Scanner;

public class Nyx {
    public static void main(String[] args) {


        HashMap<Integer, Task> tasks = new HashMap<>();
        HashMap<String, Integer> taskNameToId = new HashMap<>();
        Scanner scan = new Scanner(System.in);
        String divider = "____________________________________________________________";
        String logo = """
                ███▄▄▄▄   ▄██   ▄   ▀████    ▐████▀\s
                ███▀▀▀██▄ ███   ██▄   ███▌   ████▀ \s
                ███   ███ ███▄▄▄███    ███  ▐███   \s
                ███   ███ ▀▀▀▀▀▀███    ▀███▄███▀   \s
                ███   ███ ▄██   ███    ████▀██▄    \s
                ███   ███ ███   ███   ▐███  ▀███   \s
                ███   ███ ███   ███  ▄███     ███▄ \s
                 ▀█   █▀   ▀█████▀  ████       ███▄\s
                                                   \s""";
        System.out.println("\n" + logo + "\n");
        System.out.println(divider);
        System.out.println("Hello. I am Nyx.\n");
        System.out.println("What can I do for you?\n" + divider);
        int counter = 1;
        while (true) {
            String input = scan.nextLine();
            String[] splitInput = input.split(" ");
            if (input.equals("bye")) {
                System.out.println("Goodbye. Exiting Nyx.\n" + divider);
                break;
            }
            if (splitInput[0].equals("mark")) {
                if (splitInput.length == 2) {
                    int taskId = Integer.parseInt(splitInput[1]);
                    if (tasks.containsKey(taskId)) {
                        tasks.get(taskId).markAsComplete();
                        System.out.println("Task marked as complete: " + tasks.get(taskId) + "\n" + divider);
                    }
                } else {
                    System.out.println("Wrong usage. Correct usage: mark [task id]\n" + divider);
                }
                continue;
            }
            if (splitInput[0].equals("unmark")) {
                if (splitInput.length == 2) {
                    int taskId = Integer.parseInt(splitInput[1]);
                    if (tasks.containsKey(taskId)) {
                        tasks.get(taskId).markAsIncomplete();
                        System.out.println("Task marked as incomplete: " + tasks.get(taskId) + "\n" + divider);
                    }
                } else {
                    System.out.println("Wrong usage. Correct usage: unmark [task id]\n" + divider);
                }
                continue;
            }
            if (input.equals("list")) {
                System.out.println("Here is the current list of tasks:");
                for (Task task : tasks.values()) {
                    System.out.println(task);
                }
                System.out.println(divider);
            } else {
                if (!taskNameToId.containsKey(input)) {
                    Task newTask = new Task(input, counter);
                    tasks.put(counter, newTask);
                    taskNameToId.put(input, counter);
                    counter++;
                    System.out.println("Task created: " + input + "\n" + divider);
                } else {
                    System.out.println("Task already exists: " + input + "\n" + divider);
                }
            }
        }


    }
}
