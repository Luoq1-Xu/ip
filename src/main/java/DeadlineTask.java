public class DeadlineTask extends Task {

    private final String deadline;

    public DeadlineTask(String name, int id, String deadline) {
        super(name, id);
        this.deadline = deadline;
    }

    public String getTaskType() {
        return "D";
    }

    @Override
    public String toSaveFormat() {
        return super.toSaveFormat() + " | " + deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " Deadline: " + this.deadline;
    }
}
