package todo;

public class CustomMenuData {
    private final CustomMenuItem[] Items;
    private final CustomMenuSubItem[][] SubItems;

    public CustomMenuData(CustomMenuItem[] Items, CustomMenuSubItem[][] SubItems) {
        this.Items = Items;
        this.SubItems = SubItems;
    }

    public CustomMenuItem[] getItems() {
        return this.Items;
    }

    public CustomMenuSubItem[][] getSubItems() {
        return this.SubItems;
    }
}
