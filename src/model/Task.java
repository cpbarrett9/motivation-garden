package model;

public class Task {
    private String title;
    private String category;
    private boolean completed;

    public Task(String title, String category) {
        this.title = title;
        this.category = category;
        this.completed = false;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return (completed ? "[✔] " : "[ ] ") + category + ": " + title;
    }
}
