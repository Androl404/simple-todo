package todo;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.layout.*;

public class TodoApp {

    public static void main(String[] args) {
        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setText("Simple TODO list");
        FillLayout layout = new FillLayout();
        layout.type = SWT.VERTICAL;
        shell.setLayout(layout);

        // Icon
        Image icon16 = new Image(display, TodoApp.class.getResourceAsStream("/icon/icon16.png"));
        Image icon32 = new Image(display, TodoApp.class.getResourceAsStream("/icon/icon32.png"));
        Image icon48 = new Image(display, TodoApp.class.getResourceAsStream("/icon/icon48.png"));

        shell.setImages(new Image[] { icon16, icon32, icon48 });

        // Create all of the data for the menu bar
        // Create the accelerators
        CustomAccelerator accelerator = new CustomAccelerator("Ctrl+Q", SWT.CTRL + 'Q');

        // Create the actual data
        CustomMenuItem[] MainItems = { new CustomMenuItem("File"), new CustomMenuItem("Help")};
        CustomMenuSubItem[][] MainSubItems = {
            { new CustomMenuSubItem("Quit", accelerator, e -> shell.close())},
            { new CustomMenuSubItem("About", new CustomAccelerator(), e -> {
                        MessageBox box = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
                        box.setText("About");
                        box.setMessage("Todo App v0.1");
                        box.open();
            })}
        };
        CustomMenuData data = new CustomMenuData(MainItems, MainSubItems);

        // Create the menu bar from the data
        CustomMenuBar myMenuBar = new CustomMenuBar(shell);
        myMenuBar.addMenuFromMenuData(shell, data);

        // Main button
        // Button button = new Button(shell, SWT.PUSH);
        // button.setText("Click me");
        // button.addListener(SWT.Selection, e -> System.out.println("Clicked!"));

        // Main group
        Group group = new Group(shell, SWT.NONE);
        group.setText("Tasks");
        group.setLayout(new FillLayout());

        // Task tree
        CustomTaskTree taskTree = new CustomTaskTree(group);
        taskTree.AddColumn("Task", 300);
        taskTree.AddColumn("Category", 200);

        taskTree.AddRow(taskTree.getTree(), new String[] {"Développer cette application", "Développement"}, false, true);
        taskTree.AddRow(taskTree.getTree(), new String[] {"Acheter des merdenele", "Nourriture"}, false, true);

        // Action buttons
        Group actions = new Group(shell, SWT.NONE);
        actions.setText("Actions");
        actions.setLayout(new FillLayout());

        Button newTask = new Button(actions, SWT.PUSH);
        newTask.setText("Create new task");
        newTask.addListener(SWT.Selection, e -> {taskTree.AddRow(taskTree.getTree(), new String[] {"Nouvelle tâche", "Divers"}, false, true);});

        // Create the shell (window)
        shell.setSize(300, 200);
        shell.open();

        // Wait for dispose event
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }

        icon16.dispose(); // dispose Image resources explicitly, SWT doesn't GC them
        icon32.dispose(); // dispose Image resources explicitly, SWT doesn't GC them
        icon48.dispose(); // dispose Image resources explicitly, SWT doesn't GC them
        display.dispose();
    }
}
