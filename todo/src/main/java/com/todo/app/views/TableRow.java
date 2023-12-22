package com.todo.app.views;

import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import com.todo.app.TodoApp;
import com.todo.app.events.DelTodoBtnAdapter;
import com.todo.app.events.DoneTodoBtnAdapter;
import com.todo.app.utils.TodoItem;
import com.todo.app.utils.TodoList;

public class TableRow {

    public TableRow(Table table, TodoList todoList, int index, AtomicBoolean shouldUpdate){
        TableItem item = new TableItem(table, SWT.CENTER);
        TodoItem tItem = todoList.getItem(index);

        item.setText(0, "" + (index+1));
        item.setText(1, tItem.getTodoTxt());

        Font f = item.getFont(1);
        FontData fd = f.getFontData()[0];
        if (tItem.getIsDone()){
            fd.data.lfStrikeOut = 1;
        }
        else{
            fd.data.lfStrikeOut = 0;
        }
        Font font = new Font(table.getDisplay(), fd);
        item.setFont(1, font);

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
        doneTodoBtn.addSelectionListener(new DoneTodoBtnAdapter(item, todoList, shouldUpdate));
        doneTodoBtn.setData("ITEM_INDEX", index);
        doneTodoBtn.setSelection(tItem.getIsDone());

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
        delTodoBtn.setData("ITEM_INDEX", index);
        delTodoBtn.addSelectionListener(new DelTodoBtnAdapter(todoList, shouldUpdate));
    }
}
