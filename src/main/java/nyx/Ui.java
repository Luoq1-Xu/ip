package nyx;

import java.util.Scanner;

/**
 * The Ui class handles all interactions with the user.
 * It displays messages, reads user input, and manages the user interface.
 */
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

    /**
     * Displays the welcome message, including the logo and a divider.
     */
    public void displayWelcome() {
        System.out.println("\n" + logo + "\n");
        System.out.println(divider);
        System.out.println("Hello. I am Nyx.\n");
        System.out.println("What can I do for you?\n" + divider);
    }

    /**
     * Displays a divider line.
     */
    public void displayDivider() {
        System.out.println(divider);
    }

    /**
     * Displays the specified string.
     *
     * @param string The string to display.
     */
    public void displayString(String string) {
        System.out.println(string);
    }

    /**
     * Reads a command from the user input.
     *
     * @return The command entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Closes the scanner to clean up resources.
     */
    public void cleanup() {
        scanner.close();
    }
}
