package todo;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.layout.*;

public class TodoApp {

    public static void main(String[] args) {
        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setText("Hello SWT");
        shell.setLayout(new FillLayout());

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
        Button button = new Button(shell, SWT.PUSH);
        button.setText("Click me");
        button.addListener(SWT.Selection, e -> System.out.println("Clicked!"));

        // Create the shell (window)
        shell.setSize(300, 200);
        shell.open();

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        display.dispose();
    }
}
