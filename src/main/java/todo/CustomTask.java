package todo;

public class CustomTask {
    private String task;
    private String category;

    public CustomTask() {
        this.task = "";
        this.category = "";
    }

    public CustomTask(String task, String category) {
        this.task = task;
        this.category = category;
    }

    public String GetTask() {
        return this.task;
    }

    public String GetCategory() {
        return this.category;
    }

    public void SetTask(String task) {
        this.task = task;
    }

    public void SetCategory(String category) {
        this.category = category;
    }

}
