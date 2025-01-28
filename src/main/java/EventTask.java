public class EventTask extends Task {

    private String start;
    private String end;

    public EventTask(String name, int id, String start, String end) {
        super(name, id);
        this.start = start;
        this.end = end;
    }

    public String getTaskType() {
        return "E";
    }

    @Override
    public String toSaveFormat() {
        return super.toSaveFormat() + " | " + start + " | " + end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " Start: " + this.start + " End: " + this.end;
    }
}
