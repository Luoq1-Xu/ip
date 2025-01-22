import java.util.Scanner;

public class Nyx {
    public static void main(String[] args) {

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
            System.out.println(input);
        }


    }
}
