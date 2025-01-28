public class TodoTask extends Task {
    public TodoTask(String name, int id) {
        super(name, id);
    }

    public String getTaskType() {
        return "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
