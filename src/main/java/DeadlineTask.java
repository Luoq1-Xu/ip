import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {

    private final LocalDate deadline;

    public DeadlineTask(String name, LocalDate deadline) {
        super(name);
        this.deadline = deadline;
    }

    public String getTaskType() {
        return "D";
    }

    @Override
    public String toSaveFormat() {
        return super.toSaveFormat() + " | " + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " Deadline: " + this.deadline;
    }
}
