public class TodoTask extends Task {
    public TodoTask(String name) {
        super(name);
    }

    public String getTaskType() {
        return "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
