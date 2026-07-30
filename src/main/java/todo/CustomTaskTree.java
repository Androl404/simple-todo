package todo;

import java.util.ArrayList;

import org.eclipse.swt.widgets.*;
import org.eclipse.swt.SWT;

public class CustomTaskTree {
    private final Tree Tree;
    private ArrayList<TreeItem> Items;

    public CustomTaskTree(Composite parent) {
        this.Tree = new Tree(parent, SWT.CHECK | SWT.BORDER | SWT.FULL_SELECTION);
        this.Tree.setHeaderVisible(true);
        this.Tree.setLinesVisible(true);
        this.Items = new ArrayList<>();
    }
    
    public CustomTaskTree(Composite parent, int style) {
        this.Tree = new Tree(parent, style);
        this.Tree.setHeaderVisible(true);
        this.Tree.setLinesVisible(true);
    }

    public Tree getTree() {
        return this.Tree;
    }

    public void AddColumn(String columnName, Integer width) {
        TreeColumn nameColumn = new TreeColumn(this.Tree, SWT.LEFT);
        nameColumn.setText(columnName);
        nameColumn.setWidth(width);
    }

    public void AddColumn(String columnName, Integer width, int style) {
        TreeColumn nameColumn = new TreeColumn(this.Tree, style);
        nameColumn.setText(columnName);
        nameColumn.setWidth(width);
    }

    public void AddRow(Tree parent, String[] columnLabels, boolean setChecked, boolean setExpanded) {
        TreeItem row = new TreeItem(parent, SWT.NONE);
        row.setText(columnLabels);
        row.setChecked(setChecked);
        row.setExpanded(setExpanded);
        this.Items.add(row);
    }
    
    public void AddRow(TreeItem parent, String[] columnLabels, boolean setChecked, boolean setExpanded) {
        TreeItem row = new TreeItem(parent, SWT.NONE);
        row.setText(columnLabels);
        row.setChecked(setChecked);
        row.setExpanded(setExpanded);
        this.Items.add(row);
    }
}
