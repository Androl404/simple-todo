package todo;

public class CustomMenuItem {
    protected final String Title; // Title of the menu item

    public CustomMenuItem(String Title) {
        this.Title = Title;
    }

    public String getTitle() {
        return this.Title;
    }
}
