package todo;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.layout.*;

public class TaskEditor {
    public static CustomTask GetNewTask(Display display, Shell shell) {
        Shell taskShell = new Shell(shell, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
        taskShell.setText("Simple TODO list");

        GridLayout shellLayout = new GridLayout(1, false);
        shellLayout.marginWidth = 10;
        shellLayout.marginHeight = 10;
        shellLayout.verticalSpacing = 10;
        shellLayout.horizontalSpacing = 10;
        taskShell.setLayout(shellLayout);

        // Create the task input
        Group taskGroup = new Group(taskShell, SWT.NONE);
        taskGroup.setText("Task");
        taskGroup.setLayout(new FillLayout(SWT.HORIZONTAL));
        taskGroup.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, false));

        Text taskText = new Text(taskGroup, SWT.BORDER);

        // Create the categeory input
        Group taskCategoryGroup = new Group(taskShell, SWT.NONE);
        taskCategoryGroup.setText("Category");
        taskCategoryGroup.setLayout(new FillLayout(SWT.HORIZONTAL));
        taskCategoryGroup.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, false));

        Text categoryText = new Text(taskCategoryGroup, SWT.BORDER);

        // Validate the task creation
        Group taskValidationGroup = new Group(taskShell, SWT.NONE);
        taskValidationGroup.setText("Validation");
        taskValidationGroup.setLayout(new FillLayout(SWT.HORIZONTAL));
        GridData groupData = new GridData(SWT.FILL, SWT.BOTTOM, true, false);
        groupData.verticalIndent = 100; // pixels above the button
        taskValidationGroup.setLayoutData(groupData);

        CustomTask createdTask = new CustomTask();
        Button validateTask = new Button(taskValidationGroup, SWT.PUSH);
        validateTask.setText("Create task");
        validateTask.addListener(SWT.Selection, e -> {
                createdTask.SetTask(taskText.getText());
                createdTask.SetCategory(categoryText.getText());
                taskShell.dispose();
            });

        // Cancel the task creation
        Button cancelButton = new Button(taskValidationGroup, SWT.PUSH);
        cancelButton.setText("Cancel");
        cancelButton.addListener(SWT.Selection, e -> {
                taskShell.dispose();
            });

        // Create the shell (window)
        taskShell.setSize(600, 300);
        taskShell.open();

        // Wait for dispose event
        while (!taskShell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }

        if ((createdTask.GetTask() == "") && (createdTask.GetCategory() == "")) {
            return null;
        }
        return createdTask;

    }

//     public static Task EditTask(Task task) {
//         return;
//     }
}
