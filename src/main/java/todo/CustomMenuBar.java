package todo;

import java.util.concurrent.ExecutionException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.layout.*;

public class CustomMenuBar {
    private Menu menuBar;

    public CustomMenuBar(Shell shell) {
        this.menuBar = new Menu(shell, SWT.BAR);
        shell.setMenuBar(menuBar);
    }

    public void addMenuFromMenuData(Shell shell, CustomMenuData menuData) throws IllegalArgumentException {
        // Get elements from menuData
        CustomMenuItem[] items =  menuData.getItems();
        CustomMenuSubItem[][] subItems = menuData.getSubItems();

        // Check for irregularities in the size of the arrays
        if (items.length != subItems.length) {
            throw new IllegalArgumentException("Lenght of items and subitems is not equal.");
        }

        // Iterate in order to create the menu bar
        for (int i = 0; i < items.length; i++) {
            MenuItem menuItem = new MenuItem(this.menuBar, SWT.CASCADE);
            menuItem.setText(items[i].getTitle());

            Menu Menu = new Menu(shell, SWT.DROP_DOWN);
            menuItem.setMenu(Menu);

            // Iterate in order to create the each button with accelerator
            for (int j = 0; j < subItems.length - 1; j++) {
                MenuItem Item = new MenuItem(Menu, SWT.PUSH);
                CustomAccelerator accelerator = subItems[i][j].getCustomAccelerator();
                if (accelerator.getStringAccelerator() != null) {
                    Item.setText(subItems[i][j].getTitle() + "\t" + subItems[i][j].getCustomAccelerator().getStringAccelerator());
                } else {
                    Item.setText(subItems[i][j].getTitle());
                }
                if (accelerator.getSWTAccelerator() != null) {
                    Item.setAccelerator(subItems[i][j].getCustomAccelerator().getSWTAccelerator());
                }
                Item.addListener(SWT.Selection, subItems[i][j].getListener());
            }
        }
    }
}
