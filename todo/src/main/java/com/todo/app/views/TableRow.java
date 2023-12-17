package com.todo.app.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import com.todo.app.TodoApp;
import com.todo.app.utils.TodoItem;

public class TableRow {

    public TableRow(Table table, int index, TodoItem tItem){
        TableItem item = new TableItem(table, SWT.CENTER);
        
        item.setText(0, "" + index);
        item.setText(1, tItem.getTodoTxt());
        
        // Create the editor and button
        TableEditor doneTodoEditor = new TableEditor(table);
        Button doneTodoBtn = new Button(table, SWT.CHECK);
        Point doneBtnSize = doneTodoBtn.computeSize(SWT.DEFAULT, table.getItemHeight());
        doneTodoEditor.horizontalAlignment = SWT.CENTER;
        doneTodoEditor.verticalAlignment = SWT.CENTER;
        doneTodoEditor.minimumWidth = doneBtnSize.x;
        doneTodoEditor.minimumHeight = doneBtnSize.y;
        // Set the editor for the first column in the row
        doneTodoEditor.setEditor(doneTodoBtn, item, 2);

        // Create the editor and button
        TableEditor delTodoEditor = new TableEditor(table);
        Button delTodoBtn = new Button(table, SWT.PUSH);
        Point delBtnSize = delTodoBtn.computeSize(SWT.DEFAULT, table.getItemHeight());
        Image img = new Image(table.getDisplay(), 
                              TodoApp.class.getResourceAsStream("resources/"+"trash3.png"));
        delTodoEditor.horizontalAlignment = SWT.CENTER;
        delTodoEditor.verticalAlignment = SWT.CENTER;
        delTodoEditor.minimumWidth = delBtnSize.x;
        delTodoEditor.minimumHeight = delBtnSize.y;
        delTodoBtn.setImage(img);
        // Set the editor for the first column in the row
        delTodoEditor.setEditor(delTodoBtn, item, 3);
    }
}
