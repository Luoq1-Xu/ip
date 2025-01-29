public class Parser {

    private enum CommandType {
        TODO,
        DEADLINE,
        EVENT,
        MARK,
        UNMARK,
        DELETE,
        LIST,
        BYE,
        FILTER,
        UNKNOWN
    }

    public static Command parse(String input) {
        if (input.startsWith("todo ")) {
            return new TodoCommand(input);
        } else if (input.startsWith("deadline ")) {
            return new DeadlineCommand(input);
        } else if (input.startsWith("event ")) {
            return new EventCommand(input);
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.equals("bye")) {
            return new ByeCommand();
        } else if (input.startsWith("mark ")) {
            return new MarkCommand(input);
        } else if (input.startsWith("unmark ")) {
            return new UnmarkCommand(input);
        } else if (input.startsWith("delete ")) {
            return new DeleteCommand(input);
        } else {
            return new UnknownCommand();
        }
    }

}
