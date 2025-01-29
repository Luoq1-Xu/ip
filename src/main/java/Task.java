public abstract class Task {
    private final String name;
    private boolean completed = false;

    public Task(String name) {
        this.name = name;
    }

    public void markAsComplete() {
        this.completed = true;
    }

    public void markAsIncomplete() {
        this.completed = false;
    }

    public abstract String getTaskType();

    public int getCompletedStatus() {
        return completed ? 1 : 0;
    }

    public String getName() {
        return name;
    }

    public String toSaveFormat() {
        return this.getTaskType() + " | " + this.getCompletedStatus() + " | " + this.getName();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
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
