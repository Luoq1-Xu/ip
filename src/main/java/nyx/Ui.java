package nyx;

import java.util.Scanner;

public class Ui {

    private static final String logo = """
                                        ███▄▄▄▄   ▄██   ▄   ▀████    ▐████▀
                                        ███▀▀▀██▄ ███   ██▄   ███▌   ████▀
                                        ███   ███ ███▄▄▄███    ███  ▐███
                                        ███   ███ ▀▀▀▀▀▀███    ▀███▄███▀
                                        ███   ███ ▄██   ███    ████▀██▄
                                        ███   ███ ███   ███   ▐███  ▀███
                                        ███   ███ ███   ███  ▄███     ███▄
                                         ▀█   █▀   ▀█████▀  ████       ███▄
                                       """;
    private static final String divider = "____________________________________________________________";
    private static final Scanner scanner = new Scanner(System.in);


    public void displayWelcome() {
        System.out.println("\n" + logo + "\n");
        System.out.println(divider);
        System.out.println("Hello. I am Nyx.\n");
        System.out.println("What can I do for you?\n" + divider);
    }

    // Displays divider
    public void displayDivider () {
        System.out.println(divider);
    }

    public void displayString (String string) {
        System.out.println(string);
    }

    public String readCommand () {
        return scanner.nextLine();
    }

    public void cleanup() {
        scanner.close();
    }

}
