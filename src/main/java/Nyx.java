import java.util.HashMap;
import java.util.Scanner;

public class Nyx {
    public static void main(String[] args) {


        HashMap<String, Task> tasks = new HashMap<>();

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
        while (true) {
            String input = scan.nextLine();
            if (input.equals("bye")) {
                System.out.println("Goodbye. Exiting Nyx.\n" + divider);
                break;
            }
            if (input.equals("list")) {
                int counter = 1;
                for (Task task : tasks.values()) {
                    System.out.println(counter + ". " + task);
                    counter++;
                }
                System.out.println(divider);
            } else {
                if (!tasks.containsKey(input)) {
                    Task newTask = new Task(input);
                    tasks.put(input, newTask);
                    System.out.println("Task created: " + newTask + "\n" + divider);
                } else {
                    System.out.println("Task already exists: " + input + "\n" + divider);
                }
            }
        }


    }
}
