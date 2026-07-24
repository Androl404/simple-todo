package todo;

import org.eclipse.swt.widgets.*;

public class CustomMenuSubItem extends CustomMenuItem {
    private final CustomAccelerator Accelerator; // To be put after a tabulation, and as an accelerator
    private final Listener Listener;

    public CustomMenuSubItem(String Title, CustomAccelerator Accelerator, Listener Listener) {
        super(Title);
        this.Accelerator = Accelerator;
        this.Listener = Listener;
    }

    public String getTitle() {
        return this.Title;
    }

    public CustomAccelerator getCustomAccelerator() {
        return this.Accelerator;
    }

    public Listener getListener() {
        return this.Listener;
    }
}
