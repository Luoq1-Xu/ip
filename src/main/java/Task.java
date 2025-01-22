public class Task {
    private final String name;
    private final int id;
    private boolean completed = false;

    public Task(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public void markAsComplete() {
        this.completed = true;
    }

    public void markAsIncomplete() {
        this.completed = false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.id);
        sb.append(". ");
        if (this.completed) {
            sb.append("[X]");
        } else {
            sb.append("[ ]");
        }
        sb.append(" ");
        sb.append(this.name);
        return sb.toString();
    }
}
