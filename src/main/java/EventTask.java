import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {

    private final LocalDate start;
    private final LocalDate end;

    public EventTask(String name, int id, LocalDate start, LocalDate end) {
        super(name, id);
        this.start = start;
        this.end = end;
    }

    public String getTaskType() {
        return "E";
    }

    @Override
    public String toSaveFormat() {
        return super.toSaveFormat() + " | " + this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " | " + this.end.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " Start: "
                + this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " End: "
                + this.end.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
