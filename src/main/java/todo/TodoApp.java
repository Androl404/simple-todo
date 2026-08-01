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

        // Shell layout: 1 column, children stacked vertically
        GridLayout shellLayout = new GridLayout(1, false);
        shell.setLayout(shellLayout);

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

        // Main group (task tree) — grows to fill remaining space
        Group group = new Group(shell, SWT.NONE);
        group.setText("Tasks");
        group.setLayout(new FillLayout());
        group.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        // Actually create the tasks
        CustomTask firstTask = new CustomTask("Développer cette application", "Développement");
        CustomTask secondTask = new CustomTask("Acheter des merdenele", "Nourriture");

        CustomTaskTree taskTree = new CustomTaskTree(group);
        taskTree.AddColumn("Task", 300);
        taskTree.AddColumn("Category", 200);
        taskTree.AddRow(taskTree.getTree(), firstTask, false, true);
        taskTree.AddRow(taskTree.getTree(), secondTask, false, true);

        // Action buttons — only takes the height it needs
        Group actions = new Group(shell, SWT.NONE);
        actions.setText("Actions");
        actions.setLayout(new FillLayout(SWT.HORIZONTAL));
        actions.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, false));

        Button newTaskButton = new Button(actions, SWT.PUSH);
        newTaskButton.setText("Create new task");
        newTaskButton.addListener(SWT.Selection, e -> {
            CustomTask newTask = TaskEditor.GetNewTask(display, shell);
            if (newTask != null) {
                taskTree.AddRow(taskTree.getTree(), newTask, false, true);
            }
        });

        Button deleteTaskButton = new Button(actions, SWT.PUSH);
        deleteTaskButton.setText("Delete task");
        deleteTaskButton.addListener(SWT.Selection, e -> {
                TreeItem[] items = taskTree.getTree().getSelection();
                for (TreeItem item : items) {
                    item.dispose();
                }
        });


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
